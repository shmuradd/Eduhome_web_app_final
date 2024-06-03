package org.sb.eduhome2.dtos.course;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseDetailDto {

    private int id;
    private String name;
    private String description;
    private String image;
     private String applyDescription;
    private String certificationDescription;
    private LocalDate startDate;
    private String durationTime;
    private String classDuration;

    private String skillLevel;
    private String language;
    private int studentCapacity;
    private String assessments;
    private double price;


}
