package com.dongpl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
public class DynamicTaskSchedulerService {

    private final TaskScheduler taskScheduler;

    private final ApplicationContext applicationContext;

    private final ConcurrentHashMap<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, String> cronExpressions = new ConcurrentHashMap<>();

    @Autowired
    public DynamicTaskSchedulerService(TaskScheduler taskScheduler, ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.taskScheduler = taskScheduler;
    }

    public void scheduleTask(String taskId, String cronExpression) {
        Runnable task = () -> {
            // 在这里写入你的任务逻辑
            System.out.println("动态定时任务 " + taskId + " 执行了！");
        };

        ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(task, triggerContext -> new CronTrigger(cronExpression).nextExecutionTime(triggerContext));

        if (scheduledFuture != null) {
            scheduledTasks.put(taskId, scheduledFuture);
        }
        cronExpressions.put(taskId, cronExpression);
    }

    public void updateCronExpression(String taskId, String newCronExpression) {
        ScheduledFuture<?> scheduledFuture = scheduledTasks.get(taskId);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            scheduleTask(taskId, newCronExpression);
        }
    }

    public void removeTask(String taskId) {
        ScheduledFuture<?> scheduledFuture = scheduledTasks.get(taskId);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            scheduledTasks.remove(taskId);
            cronExpressions.remove(taskId);
        }
    }

    public Map<String, String> taskList() {
        // 使用映射获取spring容器中已存在的bean对象
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);
            Class<?> beanClass = bean.getClass();
            ReflectionUtils.doWithMethods(beanClass, method -> {
                Scheduled annotation = method.getAnnotation(Scheduled.class);
                if (annotation != null) {
                    String methodName = method.getName();
                    String annotationValue = annotation.cron();
                    cronExpressions.put(methodName, annotationValue);
                }
            });
        }
        return cronExpressions;
    }

}
