package com.teja.task_tracker_api.controller;

import com.teja.task_tracker_api.dto.TaskDTO;
import com.teja.task_tracker_api.model.Task;
import com.teja.task_tracker_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tasks")
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
}