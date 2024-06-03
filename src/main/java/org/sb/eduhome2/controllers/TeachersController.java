package org.sb.eduhome2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeachersController {
    @GetMapping("/teachers")
    public String index()
    {
        return "teacher/teachers";
    }

    @GetMapping("/teacher/detail")
    public String detail()
    {
        return "teacher/teacher-detail";
    }

}
