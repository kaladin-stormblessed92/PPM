package com.kelsier.ppm.project.impl;

import com.kelsier.ppm.core.exceptions.NotFoundException;
import com.kelsier.ppm.project.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProjectServiceImplTest {

    public static final long PROJECT_ID = 1L;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectServiceImpl;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProjects() {
        List<Project> projectList = Arrays.asList(new Project(),new Project());
        when(projectRepository.findAll()).thenReturn(projectList);

        ProjectDTOList projectDTOList = projectServiceImpl.getProjects();
        assertEquals(2,projectDTOList.getProjects().size());
        verify(projectRepository,times(1)).findAll();

    }

    @Test
    public void getProjectbyId() {

        Project project = new Project();
        project.setProjectName("PPM");
        Optional<Project> projectOptional = Optional.of(project);
        when(projectRepository.findById(PROJECT_ID)).thenReturn(projectOptional);

        ProjectDTO projectDTO = projectServiceImpl.getProjectbyId(PROJECT_ID);

        assertEquals("PPM",projectDTO.getProjectName());
        verify(projectRepository).findById(PROJECT_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getProjectbyIdThrows(){
        when(projectRepository.findById(PROJECT_ID)).thenReturn(Optional.empty());
        ProjectDTO projectDTO = projectServiceImpl.getProjectbyId(PROJECT_ID);
        verify(projectRepository).findById(PROJECT_ID);
    }

    @Test
    public void deleteProject() {
       doNothing().when(projectRepository).deleteById(PROJECT_ID);
       projectServiceImpl.deleteProject(PROJECT_ID);
       verify(projectRepository).deleteById(PROJECT_ID);

    }

    @Test
    public void createProject() {

        Project project = new Project();
        project.setProjectName("PPM");
        when(projectRepository.save(project)).thenReturn(project);


        ProjectDTO  projectRequestDTO = new ProjectDTO();
        projectRequestDTO.setProjectName("PPM");
        ProjectDTO projectDTO = projectServiceImpl.createProject(projectRequestDTO);

        assertEquals("PPM",projectDTO.getProjectName());
        verify(projectRepository).save(project);
    }
}