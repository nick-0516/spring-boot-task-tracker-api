package com.teja.task_tracker_api.controller;

import com.teja.task_tracker_api.dto.PaginatedResponse;
import com.teja.task_tracker_api.dto.TaskDTO;
import com.teja.task_tracker_api.model.Task;
import com.teja.task_tracker_api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tasks")
@SecurityRequirement(name ="BearerAuth")
public class TaskController{

    @Autowired
    private TaskService taskService;

    @PostMapping
    public TaskDTO createTask(@RequestBody @Valid Task task) {
        return taskService.addTask(task);

    }

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskService.getTaskList();
        return tasks.stream()
                .map(task -> taskService.convertToDTO(task))
                .toList();
    }
    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }
    @PutMapping("{id}")
    public TaskDTO updateTask(@PathVariable int id, @Valid @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }
    @DeleteMapping("{id}")
    public String deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return "Task deleted successfully!!";
    }
    @GetMapping("/paginated")
    public PaginatedResponse<Task> getTasksPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return taskService.getTasksPaginated(page, size, sortBy);
    }

    //filtering
    @GetMapping("/filter")
    @Operation(summary = "Filter tasks", description = "Filter tasks by completed status and/or projectId with pagination")
    public PaginatedResponse<Task> getFilteredTasks(
            @Parameter(description = "Filter by completion status (true/false)")
            @RequestParam(required = false) Boolean completed,

            @Parameter(description = "Filter by project ID")
            @RequestParam(required = false) Long projectId,

            @Parameter(description = "Page number (starts from 0)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Page size (number of records per page)")
            @RequestParam(defaultValue = "5") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Task> result = taskService.getFilteredTasks(completed, projectId, pageable);
        return new PaginatedResponse<>(
                result.getContent(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.isLast()
        );
    }
}