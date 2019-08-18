package com.kelsier.ppm.project;


public interface ProjectService {

    ProjectDTOList getProjects();

    ProjectDTO getProjectbyId(long Id);

    void deleteProject(long Id);

    ProjectDTO createProject(ProjectDTO project);
}
