package com.teja.task_tracker_api.controller;

import com.teja.task_tracker_api.dto.ProjectDTO;
import com.teja.task_tracker_api.dto.TaskDTO;
import com.teja.task_tracker_api.model.Project;
import com.teja.task_tracker_api.model.Task;
import com.teja.task_tracker_api.repository.ProjectRepository;
import com.teja.task_tracker_api.service.ProjectService;
import com.teja.task_tracker_api.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@SecurityRequirement(name ="BearerAuth")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ProjectDTO createProject(@RequestBody @Valid Project project) {
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
    public ProjectDTO getProjectById(@PathVariable int id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("{id}/tasks")
    public List<TaskDTO> getTasksByProject(@PathVariable int id) {
        Project project = projectService.getProjectEntityById(id); // returns Project entity
        List<Task> tasks = project.getTasks();
        return tasks.stream()
                .map(task -> taskService.convertToDTO(task))
                .toList();
    }

    @PutMapping("{id}")
    public ProjectDTO updateProject(@PathVariable int id,@Valid @RequestBody ProjectDTO updatedProject) {
        return projectService.updateProject(id, updatedProject);
    }

    @DeleteMapping("{id}")
    public String deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
        return "Project deleted";
    }
}
