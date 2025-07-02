package com.teja.task_tracker_api.controller;

import com.teja.task_tracker_api.dto.ProjectDTO;
import com.teja.task_tracker_api.dto.TaskDTO;
import com.teja.task_tracker_api.model.Project;
import com.teja.task_tracker_api.model.Task;
import com.teja.task_tracker_api.repository.ProjectRepository;
import com.teja.task_tracker_api.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping
    public Project createProject(@RequestBody @Valid Project project) {
        return projectService.addProject(project);
    }

    @GetMapping
    public List<ProjectDTO> GetAllProjects() {
        List<Project> projects = projectService.getProject();
        return projects.stream()
                .map(project -> projectService.convertToDTO(project))
                .toList();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable int id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("{id}/tasks")
    public List<Task> getTasksByProject(@PathVariable int id) {
        Project project = projectService.getProjectById(id);
        return project.getTasks();
    }

    @PutMapping("{id}")
    public Project updateProject(@PathVariable int id, @RequestBody Project updatedProject) {
        return projectService.updateProject(id, updatedProject);
    }

    @DeleteMapping("{id}")
    public String deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
        return "Project deleted";
    }
}
