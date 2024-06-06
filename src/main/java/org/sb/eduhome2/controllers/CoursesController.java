package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.blogs.BlogDto;
import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.dtos.course.CourseCreateDto;
import org.sb.eduhome2.dtos.course.CourseUpdateDto;
import org.sb.eduhome2.models.Course;
import org.sb.eduhome2.repositories.CourseRepository;
import org.sb.eduhome2.services.BlogService;
import org.sb.eduhome2.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class CoursesController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private CourseRepository courseRepository;

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
        List<BlogDto> blogDto=blogService.getHomeBlogs();
        CourseDetailDto courseDetailDto=courseService.courseDetail(id);
        model.addAttribute("course", courseDetailDto);
        model.addAttribute("blogs",blogDto);

        return "course/course-detail";
    }
    
    
    //Admin panel
    @GetMapping("/admin/courses")
    public String courses(Model model) {
        List<Course> courses = courseRepository.findAll().stream().toList();
        model.addAttribute("courses", courses);
        return "dashboard/course/courses";
    }
    @GetMapping("/admin/courses/create")
    public String courseCreate() {
        return "dashboard/course/course-create";
    }

    @PostMapping("/admin/courses/create")
    public String courseCreate(@ModelAttribute CourseCreateDto courseCreateDto,
                                @RequestParam(value = "imageUrl", required = false) String imageUrl) throws IOException
    {
        courseCreateDto.setImage(imageUrl);
        courseService.addCourse(courseCreateDto);
        return "redirect:/admin/courses";
    }

    @GetMapping("/admin/courses/update/{id}")
    public String courseUpdate(@PathVariable int id, Model model) {
        CourseUpdateDto courseUpdateDto=courseService.findUpdateCourse(id);
        model.addAttribute("course", courseUpdateDto);
        return "dashboard/course/update";
    }

    @PostMapping("/admin/courses/update/{id}")
    public String updatecourse(@PathVariable int id, @ModelAttribute CourseUpdateDto courseUpdateDto) {
        courseService.updateCourse(id, courseUpdateDto);
        return "redirect:/admin/courses";
    }


    @GetMapping("/admin/courses/remove/{id}")
    public String courseRemove(@ModelAttribute @PathVariable int id) {
        courseService.removeCourse(id);
        return "redirect:/admin/courses";
    }
    @GetMapping("/admin/courses/activity/{id}")
    public String activity(@PathVariable int id)
    {
        courseService.activityCourse(id);
        return "redirect:/admin/courses";
    }



}
