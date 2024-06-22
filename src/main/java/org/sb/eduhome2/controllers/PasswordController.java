package org.sb.eduhome2.controllers;

import org.sb.eduhome2.models.UserEntity;
import org.sb.eduhome2.services.EmailService;
import org.sb.eduhome2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class PasswordController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(PasswordController.class);


    @GetMapping("/forgot-password") // GET mapping for forgot password form
    public String showForgotPasswordForm() {
        return "dashboard/forgot-password"; // Return the view for forgot password form
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam("email") String email, Model model) {
        UserEntity user = userService.findUserByEmail(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            userService.savePasswordResetToken(email, token);
            emailService.sendPasswordResetEmail(email, token);
            model.addAttribute("message", "A password reset link has been sent to your email.");
        } else {
            model.addAttribute("error", "No account found with that email address.");
        }
        return "redirect:/login"; // Create this confirmation page to inform the user
    }


    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        if (userService.verifyToken(token)) {
            model.addAttribute("token", token);
            return "dashboard/reset-password";
        } else {
            model.addAttribute("error", "Invalid or expired token.");
            return "dashboard/error";
        }
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token, @RequestParam("password") String password, Model model) {
        logger.debug("Received token: {}", token);
        logger.debug("Received new password: {}", password);

        if (userService.verifyToken(token)) {
            logger.debug("Token is valid");
            userService.updatePassword(token, password);
            model.addAttribute("message", "Your password has been successfully reset.");
            return "redirect:/login";
        } else {
            logger.debug("Token is invalid or expired");
            model.addAttribute("error", "Invalid or expired token.");
            return "dashboard/error";
        }
    }
}
