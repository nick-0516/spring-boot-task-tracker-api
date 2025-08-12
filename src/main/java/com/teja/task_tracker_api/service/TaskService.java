package com.teja.task_tracker_api.service;


import com.teja.task_tracker_api.dto.PaginatedResponse;
import com.teja.task_tracker_api.dto.TaskDTO;
import com.teja.task_tracker_api.exception.ResourceNotFoundException;
import com.teja.task_tracker_api.model.Project;
import com.teja.task_tracker_api.model.Task;
import com.teja.task_tracker_api.repository.ProjectRepository;
import com.teja.task_tracker_api.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class TaskService {
    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private TaskRepository taskRepository;

    public TaskDTO addTask(Task task) {
        log.info("Adding new task with title: {}", task.getTitle());
//        int projectID = task.getProject().getId();
//        Project existingProject = projectRepository.findById(projectID)
//                .orElseThrow(() -> new RuntimeException("Project not found"));
//        task.setProject(existingProject);
        // this logic is unnecessary since @ManyToOne(has EAGER as default) used for task -> project linking in Task.java
        // (And this only works with GET, POST still has null value for project name)
        Task saved = taskRepository.save(task);
        log.debug("Task saved with ID: {}", saved.getId());
        return convertToDTO(saved);
    }
    public List<Task> getTaskList() {
        log.info("Fetching all tasks");
        return taskRepository.findAll();
    }
    public TaskDTO getTaskById(int id) {
        log.info("Fetching task with ID: {}", id);
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
        return convertToDTO(task);
    }
    public PaginatedResponse<Task> getTasksPaginated(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Task> taskPage = taskRepository.findAll(pageable);
        return new PaginatedResponse<Task>(
                taskPage.getContent(),
                taskPage.getNumber(),
                taskPage.getSize(),
                taskPage.getTotalElements(),
                taskPage.getTotalPages(),
                taskPage.isLast()
        );
    }

    public TaskDTO updateTask(int id, Task updatedTask) {
        log.info("Updating task ID: {}", id);
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
        task.setTitle(updatedTask.getTitle());
        task.setCompleted(updatedTask.isCompleted());
        Task newTask = taskRepository.save(task);
        log.info("Task ID: {} updated successfully", id);
        return convertToDTO(newTask);
    }

    public void deleteTask(int id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
        taskRepository.delete(task);
        log.info("Deleted task with ID: {}", id);
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
