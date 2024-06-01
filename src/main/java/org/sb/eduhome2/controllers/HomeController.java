package org.sb.eduhome2.controllers;

import org.sb.eduhome2.repositories.BlogRepository;
import org.sb.eduhome2.repositories.ServiceRepository;
import org.sb.eduhome2.repositories.SliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    //public HomeController(ServiceRepository serviceRepository)
    //    {
    //        this.serviceRepository=serviceRepository;
    //    }

    private ServiceRepository serviceRepository;

    @Autowired
    private SliderRepository sliderRepository;

    @Autowired
    private BlogRepository blogRepository;



    @GetMapping("/")
    public String index(Model model)
    {
        //serviceRepository.findAll();
        model.addAttribute("services",serviceRepository.findAll());
        model.addAttribute("sliders",sliderRepository.findAll());
        model.addAttribute("blogs",blogRepository.findAll());

        return "home";
    }
}
