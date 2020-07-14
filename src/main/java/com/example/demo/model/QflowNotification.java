package com.example.demo.model;

public class QflowNotification {

    private String id;

    private String taskName;

    private String notificationType;

    private String templateName;

    private String titleTemplateName;

    private String eventType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTitleTemplateName() {
        return titleTemplateName;
    }

    public void setTitleTemplateName(String titleTemplateName) {
        this.titleTemplateName = titleTemplateName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
