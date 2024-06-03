package org.sb.eduhome2.dtos.course;

import lombok.Data;

@Data
public class CourseDto {
    private Long id;
    private String name;
    private String image;
    private String description;
}
