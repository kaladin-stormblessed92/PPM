package com.kelsier.ppm.project;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {

    public static final ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);


    ProjectDTO convertProjectToProjectDTO(Project project);
    Project convertProjectDTOToProject(ProjectDTO projectDTO);
}
