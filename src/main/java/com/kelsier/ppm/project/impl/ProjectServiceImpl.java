package com.kelsier.ppm.project.impl;


import com.kelsier.ppm.core.exceptions.NotFoundException;
import com.kelsier.ppm.project.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {


    private ProjectRepository projectRepository;
    private ProjectMapper projectMapper;


    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        projectMapper = ProjectMapper.INSTANCE;
    }

    @Override
    public ProjectDTOList getProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> projectDTOS = projects.stream()
                .map(project -> projectMapper.convertProjectToProjectDTO(project))
                .collect(Collectors.toList());
        return new ProjectDTOList(projectDTOS);

    }

    @Override
    public ProjectDTO getProjectbyId(long id) throws NotFoundException {
        Project project = projectRepository.findById(id).orElse(null);
        if(project == null)
            throw new NotFoundException("Project Not Found");
        return projectMapper.convertProjectToProjectDTO(project);
    }

    @Override
    public void deleteProject(long id) {

    }

    @Override
    public ProjectDTO createProject(ProjectDTO project) {
        Project projectCreate = projectMapper.convertProjectDTOToProject(project);
        Project createdProject = projectRepository.save(projectCreate);
        return projectMapper.convertProjectToProjectDTO(createdProject);
    }
}
