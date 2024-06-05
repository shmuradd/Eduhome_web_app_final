package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.teachers.TeacherCreateDto;
import org.sb.eduhome2.dtos.teachers.TeacherDto;
import org.sb.eduhome2.dtos.teachers.TeacherUpdateDto;
import org.sb.eduhome2.models.Teacher;
import org.sb.eduhome2.repositories.TeacherRepository;
import org.sb.eduhome2.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/admin")
    public String admin() {
        return "dashboard/admin";
    }
    @GetMapping("/admin/teachers")
    public String teachers(Model model) {
        List<Teacher> teachers = teacherRepository.findAll().stream().toList();
         model.addAttribute("teachers", teachers);
        return "dashboard/teachers";
    }
    @GetMapping("/admin/teachers/create")
    public String teacherCreate() {
        return "dashboard/teacher-create";
    }

    @PostMapping("/admin/teachers/create")
    public String teacherCreate(@ModelAttribute TeacherCreateDto teacherCreateDto)
    {
        teacherService.addTeacher(teacherCreateDto);
        return "redirect:/admin/teachers";
    }

    @GetMapping("/admin/teachers/update/{id}")
    public String teacherUpdate(@PathVariable int id, Model model) {
        TeacherUpdateDto teacherUpdateDto=teacherService.findUpdateTeacher(id);
        model.addAttribute("teacher", teacherUpdateDto);
        return "dashboard/teacher/update";
    }

    @PostMapping("/admin/teachers/update")
    public String updateTeacher(@ModelAttribute TeacherUpdateDto teacherUpdateDto)
    {
        teacherService.updateTeacher(teacherUpdateDto);
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
