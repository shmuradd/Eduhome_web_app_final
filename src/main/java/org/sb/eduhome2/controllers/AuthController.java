package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.authdtos.RegisterDto;
import org.sb.eduhome2.models.UserEntity;
import org.sb.eduhome2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password. Please try again.");
        }
        return "dashboard/login"; // Return the view for the login form
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        UserEntity user = userService.findUserByEmail(username);
        if (user != null && userService.checkPassword(user, password)) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password. Please try again.");
            return "dashboard/login";
        }
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
        if (!res) {
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
