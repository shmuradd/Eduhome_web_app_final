package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.blogs.BlogDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.dtos.event.EventDto;
import org.sb.eduhome2.repositories.*;
import org.sb.eduhome2.services.BlogService;
import org.sb.eduhome2.services.CourseService;
import org.sb.eduhome2.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AboutRepository aboutRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EventService eventService;

    @Autowired
    private StudentCommentsRepository studentCommentsRepository;

    @Autowired
    private BlogService blogService;


    @GetMapping("/")
    public String index(Model model)
    {
        List<CourseDto> courseDto= courseService.getHomeCourses();
        List<EventDto> eventDto=eventService.getHomeEvents();
        List<BlogDto> blogDto=blogService.getHomeBlogs();
        model.addAttribute("services",serviceRepository.findAll());
        model.addAttribute("sliders",sliderRepository.findAll());
        model.addAttribute("about",aboutRepository.findAll().getFirst());
        model.addAttribute("studentComments", studentCommentsRepository.findAll());
        model.addAttribute("courses",courseDto);
        model.addAttribute("events",eventDto);
        model.addAttribute("blogs",blogDto);


        return "home";
    }



}
