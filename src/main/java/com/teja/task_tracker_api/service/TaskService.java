package com.teja.task_tracker_api.service;


import com.teja.task_tracker_api.dto.TaskDTO;
import com.teja.task_tracker_api.exception.ResourceNotFoundException;
import com.teja.task_tracker_api.model.Project;
import com.teja.task_tracker_api.model.Task;
import com.teja.task_tracker_api.repository.ProjectRepository;
import com.teja.task_tracker_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
//    @Autowired
//    private ProjectRepository projectRepository;

    public TaskDTO addTask(Task task) {
//        int projectID = task.getProject().getId();
//        Project existingProject = projectRepository.findById(projectID)
//                .orElseThrow(() -> new RuntimeException("Project not found"));
//        task.setProject(existingProject);
        // this logic is unnecessary since @ManyToOne(has EAGER as default) used for task -> project linking in Task.java
        // (And this only works with GET, POST still has null value for project name)
        taskRepository.save(task);
        return convertToDTO(task);
    }
    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }
    public TaskDTO getTaskById(int id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
        return convertToDTO(task);
    }

    public TaskDTO updateTask(int id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
        task.setTitle(updatedTask.getTitle());
        task.setCompleted(updatedTask.isCompleted());
        Task newTask = taskRepository.save(task);
        return convertToDTO(newTask);
    }

    public void deleteTask(int id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
        taskRepository.delete(task);
    }

    public TaskDTO convertToDTO(Task task) {
        Project project = task.getProject();

        //to avoid NullPointerException, incase if a task doesn't have a project.
        int projectId = project != null ? project.getId() : 0;
        String projectName = project != null ? project.getName() : null;

        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.isCompleted(),
                projectId,
                projectName
        );
    }
}
