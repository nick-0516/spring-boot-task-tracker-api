package com.teja.task_tracker_api.dto;


import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;


public class ProjectDTO {
    private int id;
    @NotNull
    @Size(min = 3, max = 50)
    private String name;


    public ProjectDTO() {}

    public ProjectDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
