package org.sb.eduhome2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactsController {
    @GetMapping("/contacts")
    public String index(Model model)
    {
        model.addAttribute("page","contact");
        return "contacts";
    }
}
