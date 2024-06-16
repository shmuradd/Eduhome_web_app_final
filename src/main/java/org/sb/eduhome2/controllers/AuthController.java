package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.authdtos.RegisterDto;
import org.sb.eduhome2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login()
    {
        return "dashboard/login";
    }

    @GetMapping("/yes")
    public String yes()
    {
        return "dashboard/login";
    }


    @GetMapping("/register")
    public String register()
    {
        return "dashboard/register";
    }


    @PostMapping("/register")
    public String register(RegisterDto registerDto)
    {
        boolean res = userService.register(registerDto);
        if (res == false) {
            return "dashboard/register";
        }
        return "redirect:/login";
    }

    @GetMapping("auth/confrim")
    public String confirm(String email, String token)
    {
        boolean res = userService.confirmEmail(email, token);
        return "redirect:/login";
    }
}
