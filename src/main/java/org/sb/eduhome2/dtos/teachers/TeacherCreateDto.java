package org.sb.eduhome2.dtos.teachers;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TeacherCreateDto {
    private String name;
    private String surname;
    private String image;
    private String job;
    private String facebookUrl;


    private Integer communicationPoint;

    private Integer developmentPoint;

    private Integer innovationPoint;

    private Integer designPoint;

    private Integer teamLeaderPoint;

    private Integer languagePoint;


    private String phoneNumbers;


    private String email;

    private String hobbies;

    private String experience;

    private String degree;


    private String aboutTeacher;

}
