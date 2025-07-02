package com.teja.task_tracker_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectDTO addProject(Project project) {
        log.info("Creating new project with name: {}", project.getName());
        Project prj = projectRepository.save(project);
        log.debug("Project created with ID: {}", prj.getId());
        return convertToDTO(prj);
    }
    public List<Project> getProject() {
        log.info("Fetching all projects");
        return projectRepository.findAll();
    }
    public ProjectDTO getProjectById(int id) {
        log.info("Fetching project with ID: {}", id);
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));
        return convertToDTO(project);
    }

    public ProjectDTO updateProject(int id, ProjectDTO updatedProject) {
        log.info("Updating project ID: {}", id);
        Project existingProject = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));
        existingProject.setName(updatedProject.getName());
        Project saved = projectRepository.save(existingProject);
        log.info("Project ID: {} updated successfully", id);
        return convertToDTO(saved);
    }

    public void deleteProject(int id) {
        Project delProject = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));
        projectRepository.delete(delProject);
        log.info("Deleting project with ID: {} ", id);

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

    //to return project entity, not projectDTO
    public Project getProjectEntityById(int id) {
        log.info("Fetching project with ID: {}", id);
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

}
