package com.dongpl.controller;

import com.dongpl.service.DynamicTaskSchedulerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class ArticleController {

    @Resource
    private DynamicTaskSchedulerService dynamicTaskSchedulerService;

    @GetMapping("/taskList")
    public Map<String, String> taskList() {
        return dynamicTaskSchedulerService.taskList();
    }

    @GetMapping("/scheduleTask")
    public String scheduleTask(@RequestParam("taskId") String taskId, @RequestParam("cron") String cron) {
        dynamicTaskSchedulerService.scheduleTask(taskId, cron);
        return "创建一个名为 \"" + taskId + "\" 的定时任务，每5秒执行一次";
    }

    @GetMapping("/updateCronExpression")
    public String updateCronExpression(@RequestParam("taskId") String taskId, @RequestParam("cron") String cron) {
        dynamicTaskSchedulerService.updateCronExpression(taskId, cron);
        return "修改名为 \"" + taskId + "\" 的定时任务的cron表达式为每10秒执行一次";
    }

    @GetMapping("/removeTask")
    public String removeTask(@RequestParam("taskId") String taskId) {
        dynamicTaskSchedulerService.removeTask(taskId);
        return "移除名为 \"" + taskId + "\" 的定时任务";
    }

}
