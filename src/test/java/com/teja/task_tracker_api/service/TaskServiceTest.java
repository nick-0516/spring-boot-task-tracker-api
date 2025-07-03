package com.teja.task_tracker_api.service;


import com.teja.task_tracker_api.dto.TaskDTO;
import com.teja.task_tracker_api.exception.ResourceNotFoundException;
import com.teja.task_tracker_api.model.Task;
import com.teja.task_tracker_api.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskService taskService;

    @Test
    public void testAddTask() {
        Task taskInput = new Task();
        taskInput.setTitle("test_title");
        taskInput.setCompleted(false);

        Task savedTask = new Task();
        savedTask.setTitle("test_title");
        savedTask.setCompleted(false);

        when(taskRepository.save(taskInput)).thenReturn(savedTask);
        TaskDTO resultTask = taskService.addTask(taskInput);

        assertNotNull(resultTask);
        assertEquals("test_title", resultTask.getTitle());
        assertFalse(resultTask.isCompleted());

        verify(taskRepository, times(1)).save(taskInput);
    }

    @Test
    public void testGetTaskById() {
        Task savedTask = new Task();
        savedTask.setTitle("test_title");
        savedTask.setCompleted(false);
        savedTask.setId(1);

        when(taskRepository.findById(1)).thenReturn(Optional.of(savedTask));
        TaskDTO result = taskService.getTaskById(1);

        assertNotNull(result);
        assertEquals("test_title", result.getTitle());
        assertEquals(1, result.getId());
        assertFalse(result.isCompleted());
        verify(taskRepository, times(1)).findById(1);
    }

    @Test
    public void testGetTaskById_notFound() {
        int taskId = 99;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.getTaskById(taskId));

        assertNotNull(thrown);
        assertEquals("Task not found with ID: 99", thrown.getMessage());

        verify(taskRepository, times(1)).findById(taskId);
    }
}
