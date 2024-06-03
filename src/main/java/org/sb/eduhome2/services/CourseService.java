package org.sb.eduhome2.services;

import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;

import java.util.List;

public interface CourseService {
    List<CourseDto> getCourses();

    List<CourseDto> getHomeCourses();

    CourseDetailDto courseDetail(int id);
}
