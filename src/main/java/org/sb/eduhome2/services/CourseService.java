package org.sb.eduhome2.services;

import org.sb.eduhome2.dtos.course.CourseCreateDto;
import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.dtos.course.CourseUpdateDto;
import org.sb.eduhome2.dtos.teachers.TeacherCreateDto;
import org.sb.eduhome2.dtos.teachers.TeacherUpdateDto;
import org.sb.eduhome2.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CourseService {
    public Page<CourseDto> getCourses(PageRequest pageRequest);
    List<CourseDto> getHomeCourses();

    CourseDetailDto courseDetail(int id);


    void addCourse(CourseCreateDto courseCreateDto);

    CourseUpdateDto findUpdateCourse(int id);
    public void updateCourse(int id, CourseUpdateDto courseUpdateDto);
    public void removeCourse(int courseId);
    public void activityCourse(int courseId);

    public List<Course> searchCourses(String query);

}
