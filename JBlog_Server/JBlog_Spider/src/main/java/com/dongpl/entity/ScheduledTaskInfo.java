package com.dongpl.entity;

import lombok.Data;

@Data
public class ScheduledTaskInfo {

    private String taskId;

    private String cronExpression;

    public ScheduledTaskInfo(String taskId, String cronExpression) {
        this.taskId = taskId;
        this.cronExpression = cronExpression;
    }

}
