package com.teja.task_tracker_api.dto;

import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;

public class TaskDTO {
    private int id;
    @NotNull
    @Size(min = 3, max = 50)
    private String title;
    private boolean completed;
    private int projectId;
    private String projectName;

    public TaskDTO() {}

    public TaskDTO(int id, String title, boolean completed, int projectId, String projectName) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
