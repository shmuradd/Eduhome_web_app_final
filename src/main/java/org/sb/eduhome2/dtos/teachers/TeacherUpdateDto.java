package org.sb.eduhome2.dtos.teachers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
