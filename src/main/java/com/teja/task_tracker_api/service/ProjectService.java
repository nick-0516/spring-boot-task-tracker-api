package com.teja.task_tracker_api.service;

import com.teja.task_tracker_api.exception.ResourceNotFoundException;
import com.teja.task_tracker_api.model.Project;
import com.teja.task_tracker_api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project addProject(Project project) {
        return projectRepository.save(project);
    }
    public List<Project> getProject() {
        return projectRepository.findAll();
    }
    public Project getProjectById(int id) {
        return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));
    }

    public Project updateProject(int id, Project updatedProject) {
        Project existingProject = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));
        existingProject.setName(updatedProject.getName());
        return projectRepository.save(existingProject);
    }

    public void deleteProject(int id) {
        Project delProject = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));
        projectRepository.delete(delProject);
//      projectRepository.deleteById(id); this can't be used.
        // you wouldn't know if there was a project with corresponding id or not, ProjectController will always return success string.
    }

}
