package com.kelsier.ppm.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Project {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{project.name.notnull}")
    private String projectName;
    @NotBlank(message = "{project.description.notnull}")
    private String description;
    @NotBlank(message = "{project.identifier.notnull}")
    //@Size(min=4,max=5,message = "{project.identifier.size}")
    private String projectIdentifier;

    //@JsonFormat(pattern = "yyyy-mm-dd")
    //@NotBlank(message = "{project.start_date.notnull}")
    private Date start_date;

    //@JsonFormat(pattern = "yyyy-mm-dd")
    //@NotBlank(message = "{project.end_date.notnull}")
    private Date end_date;

    private Date created_at;
    private Date updated_at;

    @PrePersist
    public void setCreated(){
        this.created_at = new Date();
    }

    @PreUpdate
    public void setUpdated(){
        this.updated_at = new Date();
    }
}
