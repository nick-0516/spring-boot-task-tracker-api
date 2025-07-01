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
    // POST /tasks → Add a new task
    @PostMapping
    public Task createTask(@RequestBody @Valid Task task) {
        taskService.addTask(task);
        return task; // Just return the same object for now
    }
    // GET /tasks → Get all tasks
    @GetMapping
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskService.getTaskList();
        return tasks.stream()
                .map(task -> taskService.convertToDTO(task))
                .toList();
    }
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }
    @PutMapping("{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }
    @DeleteMapping("{id}")
    public String deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return "Task deleted successfully!!";
    }
}