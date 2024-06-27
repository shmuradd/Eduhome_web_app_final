package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.blogs.BlogDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.dtos.event.EventDto;
import org.sb.eduhome2.models.EmailSubscription;
import org.sb.eduhome2.repositories.*;
import org.sb.eduhome2.services.BlogService;
import org.sb.eduhome2.services.CourseService;
import org.sb.eduhome2.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

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

    @Autowired
    private EmailSubscriptionRepository emailSubscriptionRepository;

    private Set<String> subscribedEmails = new HashSet<>();

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
    @GetMapping("/subscribe")
    public String subscribeSuccess(Model model) {
        // Add any necessary attributes to the model
        return "fragments/subscribeSuccess";
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Map<String, Object>> subscribe(@RequestParam("email") String email) {
        Map<String, Object> response = new HashMap<>();

        if (subscribedEmails.contains(email)) {
            response.put("success", false);
            response.put("message", "This email is already subscribed.");
        } else {
            // Add to subscribedEmails set
            subscribedEmails.add(email);

            // Save to repository
            EmailSubscription subscription = new EmailSubscription();
            subscription.setEmail(email);
            emailSubscriptionRepository.save(subscription);

            response.put("success", true);
            response.put("email", email);
        }

        return ResponseEntity.ok(response);
    }

}
