package com.kelsier.ppm.project;

import com.kelsier.ppm.project.impl.ProjectServiceImpl;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ProjectControllerTest {

    MockMvc mockMvc;

    @Mock
    private ProjectServiceImpl projectService;
    @InjectMocks
    private ProjectController projectController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    public void getProjects() throws Exception {

        when(projectService.getProjects()).
                thenReturn(
                        new ProjectDTOList(Arrays.asList(new ProjectDTO(),new ProjectDTO()))
                );

         mockMvc.perform(get("/api/v1/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projects",hasSize(2)));



    }

    @Test
    public void createProject() throws Exception {
        JSONObject json = new JSONObject();
        json.put("projectName","PPM");
        json.put("description","PPM Desc");
        json.put("projectIdentifier","PPMP");

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName("PPM");

        when(projectService.createProject(any(ProjectDTO.class))).thenReturn(projectDTO);
        mockMvc.perform(post("/api/v1/projects").contentType(MediaType.APPLICATION_JSON)
                .content(json.toString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.projectName",equalTo("PPM")));

        verify(projectService).createProject(any(ProjectDTO.class));
    }

    @Test
    public void getProject() throws Exception {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName("PPM");


        when(projectService.getProjectbyId(1L)).thenReturn(projectDTO);
        mockMvc.perform(get("/api/v1/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectName",equalTo("PPM")));
    }

    @Test
    public void deleteProject() throws Exception {
        doNothing().when(projectService).deleteProject(anyLong());
        mockMvc.perform(delete("/api/v1/projects/1"))
                .andExpect(status().isOk());
    }

}