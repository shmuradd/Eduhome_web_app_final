package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.teachers.TeacherDto;
import org.sb.eduhome2.repositories.AboutRepository;
import org.sb.eduhome2.repositories.StudentCommentsRepository;
import org.sb.eduhome2.repositories.TeacherRepository;
import org.sb.eduhome2.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AboutController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentCommentsRepository studentCommentsRepository;
    @Autowired
    private AboutRepository aboutRepository;
    @GetMapping("/about")
    public String index(Model model)
    {
        List<TeacherDto> teachers=teacherService.getAboutTeachers();
        model.addAttribute("about",aboutRepository.findAll().getFirst());
        model.addAttribute("studentComments", studentCommentsRepository.findAll());
        model.addAttribute("teachers",teachers);
        return "about/about";
    }
}



