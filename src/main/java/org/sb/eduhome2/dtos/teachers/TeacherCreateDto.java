package org.sb.eduhome2.dtos.teachers;

import jakarta.persistence.Column;
import lombok.Data;

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


    private int communicationPoint;

    private int developmentPoint;

    private int innovationPoint;

    private int designPoint;

    private int teamLeaderPoint;

    private int languagePoint;


    private String phoneNumbers;


    private String email;

    private String hobbies;

    private String experience;

    private String degree;


    private String aboutTeacher;

}
