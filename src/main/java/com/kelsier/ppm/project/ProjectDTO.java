package com.kelsier.ppm.project;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProjectDTO {

    private Long id;

    @NotBlank(message = "{project.name.notnull}")
    @NotBlank(message = "{project.name.notnull}")
    private String projectName;
    @NotBlank(message = "{project.description.notnull}")
    @NotBlank(message = "{project.description.notnull}")
    private String description;
    @NotBlank(message = "{project.identifier.notnull}")
    @NotBlank(message = "{project.identifier.notnull}")
    //@Size(min=4,max=5,message = "{project.identifier.size}")
    private String projectIdentifier;

    //@JsonFormat(pattern = "yyyy-mm-dd")
    //@NotBlank(message = "{project.start_date.notnull}")
    private Date start_date;

    //@JsonFormat(pattern = "yyyy-mm-dd")
    //@NotBlank(message = "{project.end_date.notnull}")
    private Date end_date;



}
