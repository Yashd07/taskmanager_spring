package com.yash.taskmanager.dto;

public class UpdateTaskDTO {
    String description;
    String deadline;
    Boolean completed;

    public UpdateTaskDTO(String description, String deadline, Boolean completed) {
        this.description = description;
        this.deadline = deadline;
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
