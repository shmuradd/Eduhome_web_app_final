package org.sb.eduhome2.controllers;
import java.util.Comparator;

import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.dtos.teachers.TeacherCreateDto;
import org.sb.eduhome2.dtos.teachers.TeacherDetailDto;
import org.sb.eduhome2.dtos.teachers.TeacherDto;
import org.sb.eduhome2.dtos.teachers.TeacherUpdateDto;
import org.sb.eduhome2.models.Teacher;
import org.sb.eduhome2.repositories.TeacherRepository;
import org.sb.eduhome2.services.CourseService;
import org.sb.eduhome2.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class TeachersController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherRepository teacherRepository;

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

    @GetMapping("/admin/teachers")
    public String teachers(Model model) {
        List<Teacher> teachers = teacherRepository.findAll()
                .stream()
                .sorted(Comparator.comparingLong(Teacher::getId))
                .toList();
        model.addAttribute("teachers", teachers);
        return "dashboard/teacher/teachers";
    }

    @GetMapping("/admin/teachers/create")
    public String teacherCreate() {
        return "dashboard/teacher/teacher-create";
    }

    @PostMapping("/admin/teachers/create")
    public String teacherCreate(@ModelAttribute TeacherCreateDto teacherCreateDto,
                                @RequestParam(value = "imageUrl", required = false) String imageUrl) throws IOException
    {
        teacherCreateDto.setImage(imageUrl);
        teacherService.addTeacher(teacherCreateDto);
        return "redirect:/admin/teachers";
    }

    @GetMapping("/admin/teachers/update/{id}")
    public String teacherUpdate(@PathVariable int id, Model model) {
        TeacherUpdateDto teacherUpdateDto=teacherService.findUpdateTeacher(id);
        model.addAttribute("teacher", teacherUpdateDto);
        return "dashboard/teacher/update";
    }

    @PostMapping("/admin/teachers/update/{id}")
    public String updateTeacher(@PathVariable int id, @ModelAttribute TeacherUpdateDto teacherUpdateDto) {
        teacherService.updateTeacher(id, teacherUpdateDto);
        return "redirect:/admin/teachers";
    }


    @GetMapping("/admin/teachers/remove/{id}")
    public String teacherRemove(@ModelAttribute @PathVariable int id) {
        teacherService.removeTeacher(id);
        return "redirect:/admin/teachers";
    }
    @GetMapping("/admin/teachers/activity/{id}")
    public String activity(@PathVariable int id)
    {
        teacherService.activityTeacher(id);
        return "redirect:/admin/teachers";
    }
}
