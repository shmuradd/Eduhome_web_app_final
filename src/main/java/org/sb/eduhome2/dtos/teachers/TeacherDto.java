package org.sb.eduhome2.dtos.teachers;

import jakarta.persistence.Column;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
public class TeacherDto {
    private int id;
    private String name;
    private String surname;
    private String image;
    private String job;
    private String facebookUrl;


}
