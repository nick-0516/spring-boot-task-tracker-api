package com.teja.task_tracker_api.service;

import com.teja.task_tracker_api.dto.ProjectDTO;
import com.teja.task_tracker_api.model.Project;
import com.teja.task_tracker_api.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    public void testCreateProject_returnsProjectDTO() {
        Project projectInput = new Project();
        projectInput.setName("Learn Java-Test");

        Project savedProject = new Project();
        savedProject.setName("Learn Java-Test");
        savedProject.setId(1);

        when(projectRepository.save(projectInput)).thenReturn(savedProject);
        ProjectDTO result = projectService.addProject(projectInput);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Learn Java-Test", result.getName());
        verify(projectRepository, times(1)).save(projectInput);
    }
    @Test
    public void testgetProjectEntityById(){
        Project savedProject = new Project();
        savedProject.setName("Learn Java-Test");
        savedProject.setId(1);
        when(projectRepository.findById(1)).thenReturn(Optional.of(savedProject));
        ProjectDTO result = projectService.getProjectById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Learn Java-Test", result.getName());
        verify(projectRepository, times(1)).findById(1);

    }
}
