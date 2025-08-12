package com.teja.task_tracker_api.controller;

import com.teja.task_tracker_api.dto.PaginatedResponse;
import com.teja.task_tracker_api.dto.TaskDTO;
import com.teja.task_tracker_api.model.Task;
import com.teja.task_tracker_api.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
}