package com.kelsier.ppm.project;


import com.kelsier.ppm.core.validators.FieldValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {


    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<ProjectDTOList> getProjects(){
       return new ResponseEntity<ProjectDTOList>(projectService.getProjects(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectDTO projectDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return FieldValidator.getFieldErrors(bindingResult);
        }
        return new ResponseEntity<ProjectDTO>(projectService.createProject(projectDTO),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable long id){
        return new ResponseEntity<ProjectDTO>(projectService.getProjectbyId(id),HttpStatus.OK);
    }
}
