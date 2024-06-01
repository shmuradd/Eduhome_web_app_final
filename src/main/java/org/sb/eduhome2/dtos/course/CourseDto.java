package org.sb.eduhome2.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CourseDto {
    private Long id;
    private String name;
    private String image;
    private String description;
}
