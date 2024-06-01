package org.sb.eduhome2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoursesController {
    @GetMapping("/courses")
    public String index()
    {
        return "courses";
    }


    @GetMapping("/courses/detail")
    public String detail()
    {
        return "courseDetail";
    }



}
