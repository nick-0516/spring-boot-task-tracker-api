package com.teja.task_tracker_api.service;

import com.teja.task_tracker_api.dto.ProjectDTO;
import com.teja.task_tracker_api.dto.TaskDTO;
import com.teja.task_tracker_api.exception.ResourceNotFoundException;
import com.teja.task_tracker_api.model.Project;
import com.teja.task_tracker_api.model.Task;
import com.teja.task_tracker_api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectDTO addProject(Project project) {
        Project prj = projectRepository.save(project);
        return convertToDTO(prj);
    }
    public List<Project> getProject() {
        return projectRepository.findAll();
    }
    public ProjectDTO getProjectById(int id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));
        return convertToDTO(project);
    }

    public ProjectDTO updateProject(int id, ProjectDTO updatedProject) {
        Project existingProject = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));
        existingProject.setName(updatedProject.getName());
        Project saved = projectRepository.save(existingProject);
        return convertToDTO(saved);
    }

    public void deleteProject(int id) {
        Project delProject = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));
        projectRepository.delete(delProject);
      //projectRepository.deleteById(id); this can't be used.
        // we wouldn't know if there was a project with corresponding id or not,
        // ProjectController will always return success string.
    }

    public ProjectDTO convertToDTO(Project project) {

        return new ProjectDTO(
                project.getId(),
                project.getName()
        );
    }

    public Project getProjectEntityById(int id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

}
