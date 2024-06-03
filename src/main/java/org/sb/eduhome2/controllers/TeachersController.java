package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.dtos.teachers.TeacherDetailDto;
import org.sb.eduhome2.dtos.teachers.TeacherDto;
import org.sb.eduhome2.services.CourseService;
import org.sb.eduhome2.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TeachersController {
    @Autowired
    private TeacherService teacherService;


    @GetMapping("/teachers")
    public String index(Model model)
    {
        List<TeacherDto> teachers= teacherService.getTeachers();

        model.addAttribute("teachers", teachers);
        return "teacher/teachers";
    }

    @GetMapping("/teachers/{id}")
    public String detail(@PathVariable int id, Model model)
    {
        TeacherDetailDto teacherDetailDto=teacherService.teacherDetail(id);
        model.addAttribute("teacher", teacherDetailDto);
        return "teacher/teacher-detail";
    }
}
