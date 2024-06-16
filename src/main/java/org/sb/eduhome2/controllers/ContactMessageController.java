package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.course.CourseCreateDto;
import org.sb.eduhome2.dtos.replies.ContactCommentDto;
import org.sb.eduhome2.models.ContactMessage;
import org.sb.eduhome2.repositories.ContactMessageRepository;
import org.sb.eduhome2.services.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class ContactMessageController {


    @Autowired
    private ContactMessageRepository messageRepository;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ContactMessageService messageService;


    @PostMapping
    public String saveMessage(
            @ModelAttribute ContactCommentDto message) {


        messageService.saveMessage(message);
        return "redirect:/messages";
    }


    @GetMapping
    public List<ContactMessage> getMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping("/reply/{id}")
    public ContactMessage replyToMessage(@PathVariable Long id, @RequestBody String reply) {
        return messageService.replyToMessage(id, reply);
    }
}
