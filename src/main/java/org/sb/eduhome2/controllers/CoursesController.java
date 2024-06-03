package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CoursesController {

    @Autowired
    private CourseService courseService;


    @GetMapping("/courses")
    public String index(Model model)
    {
        List<CourseDto> courses= courseService.getCourses();
        model.addAttribute("courses", courses);
        return "course/courses";
    }


    @GetMapping("/courses/{id}")
    public String detail(@PathVariable int id, Model model)
    {
        CourseDetailDto courseDetailDto=courseService.courseDetail(id);
        model.addAttribute("course", courseDetailDto);
        return "course/course-detail";
    }



}
