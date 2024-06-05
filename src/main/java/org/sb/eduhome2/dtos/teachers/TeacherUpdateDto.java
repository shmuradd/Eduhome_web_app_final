package org.sb.eduhome2.dtos.teachers;

import lombok.Data;

@Data
public class TeacherUpdateDto {
    private int id;
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
