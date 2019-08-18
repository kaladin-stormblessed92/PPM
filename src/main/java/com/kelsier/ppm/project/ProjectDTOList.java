package com.kelsier.ppm.project;

import com.kelsier.ppm.project.ProjectDTO;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class ProjectDTOList {

    private List<ProjectDTO> projects;
}
