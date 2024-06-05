package org.sb.eduhome2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String image;
    private String job;
    private String facebookUrl;

    @Column(nullable = false)
    private Boolean isDeleted;

    private int communicationPoint;

    private int developmentPoint;

    private int innovationPoint;

    private int designPoint;

    private int teamLeaderPoint;

    private int languagePoint;


    @NotBlank(message = "Boş saxlanıla Bilməz!")
    private String phoneNumbers;

    @NotBlank(message = "Boş saxlanıla Bilməz!")
    @Email(message = "Düzgün E-poçt ünvanı daxil etdiyinizdən əmin olun!")
    private String email;

    @NotBlank(message = "Boş saxlanıla Bilməz!")
    private String hobbies;

    @NotBlank(message = "Boş saxlanıla Bilməz!")
    private String experience;

    @NotBlank(message = "Boş saxlanıla Bilməz!")
    private String degree;

    @NotBlank(message = "Boş saxlanıla Bilməz!")
    @Size(max = 150, message = "Max 150 simvol istifadə edilə bilər!")
    private String aboutTeacher;

}
