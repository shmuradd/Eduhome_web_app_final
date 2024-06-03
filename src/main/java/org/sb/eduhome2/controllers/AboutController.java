package org.sb.eduhome2.controllers;

import org.sb.eduhome2.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/about")
    public String index(Model model)
    {

        return "about/about";
    }
}



