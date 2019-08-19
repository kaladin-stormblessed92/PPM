package com.kelsier.ppm.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectRepositoryIT {


    @Autowired
    private ProjectRepository projectRepository;
    private Project projectSaved = null;

    @Before
    public void setup(){

        Project project = new Project();
        project.setProjectName("PPM");
        project.setDescription("Description");
        project.setProjectIdentifier("PPM1");
        projectSaved = projectRepository.save(project);

    }

    @Test
    public void save(){
        assertEquals("PPM",projectSaved.getProjectName());

    }

    @Test
    public void findById(){
       Project project =  projectRepository.findById(projectSaved.getId()).orElse(null);
        assertEquals (projectSaved.getId(),project.getId());

    }

    @Test
    public void findAll(){
        List<Project> projects = projectRepository.findAll();
        assertEquals(1,projects.size());
    }

    @Test
    public void deleteById(){
        try{
            projectRepository.deleteById(projectSaved.getId());
        }catch (Error e){
            throw new Error("Failed to delete Project");
        }

    }

    @After
    public void tearDown(){
        projectRepository.deleteAll();
    }





}