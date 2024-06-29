package org.sb.eduhome2.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

public class CustomErrorController {
    @RequestMapping("/error")
    public String handleError() {
        // Return the name of your error page
        return "error";
    }
}
