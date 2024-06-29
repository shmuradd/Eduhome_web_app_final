package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.blogs.BlogDto;
import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.dtos.event.EventCreateDto;
import org.sb.eduhome2.dtos.event.EventDetailDto;
import org.sb.eduhome2.dtos.event.EventDto;
import org.sb.eduhome2.dtos.event.EventUpdateDto;
import org.sb.eduhome2.models.Event;
import org.sb.eduhome2.models.Speaker;
import org.sb.eduhome2.repositories.EventRepository;
import org.sb.eduhome2.repositories.SpeakerRepository;
import org.sb.eduhome2.services.BlogService;
import org.sb.eduhome2.services.CourseService;
import org.sb.eduhome2.services.EventService;
import org.sb.eduhome2.services.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller
public class EventsController {

    @Autowired
    private EventService eventService;

    @Autowired
    private SpeakerService speakerService;
    @Autowired
    private BlogService blogService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SpeakerRepository speakerRepository;


    @GetMapping("/events")
    public String index(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "6") int size)
    {
        Page<EventDto> eventsPage= eventService.getEvents(PageRequest.of(page,size));

        if (eventsPage.hasContent())
        {
            model.addAttribute("events", eventsPage.getContent());

        }
        else {
            model.addAttribute("events", Collections.emptyList());

        }
        model.addAttribute("eventsPage", eventsPage);


        return "event/events";
    }



    @GetMapping("/events/{id}")
    public String detail(@PathVariable int id, Model model)
    {

        EventDetailDto eventDetailDto=eventService.eventDetail(id);
        List<Speaker> speakers=speakerService.getSpeakersByEventId(id);
        List<BlogDto> blogDto=blogService.getHomeBlogs();

        model.addAttribute("event", eventDetailDto);
        model.addAttribute("speakers",speakers);
        model.addAttribute("blogs",blogDto);

        return "event/event-detail";
    }
    // Admin panel
    @GetMapping("/admin/events")
    public String events(Model model) {
        List<Event> events = eventRepository.findAll().stream().toList();
        model.addAttribute("speakers", speakerRepository.findAll().stream().toList());

        model.addAttribute("events", events);
        return "dashboard/event/events";
    }

    @GetMapping("/admin/events/create")
    public String eventCreate(Model model) {
        model.addAttribute("speakers", speakerRepository.findAll().stream().toList());

        return "dashboard/event/event-create";
    }

    @PostMapping("/admin/events/create")
    public String eventCreate(@ModelAttribute EventCreateDto eventCreateDto,
                              @RequestParam(value = "imageUrl", required = false) String imageUrl) throws IOException {
        eventService.addEvent(eventCreateDto);
        return "redirect:/admin/events";
    }

    @GetMapping("/admin/events/update/{id}")
    public String eventUpdate(@PathVariable int id, Model model) {
        EventUpdateDto eventUpdateDto = eventService.findUpdateEvent(id);
        model.addAttribute("event", eventUpdateDto);
        model.addAttribute("speakers", speakerRepository.findAll().stream().toList());
        return "dashboard/event/update";
    }

    @PostMapping("/admin/events/update/{id}")
    public String updateEvent(@PathVariable int id, @ModelAttribute EventUpdateDto eventUpdateDto) {
        eventService.updateEvent(id, eventUpdateDto);
        return "redirect:/admin/events";
    }

    @GetMapping("/admin/events/remove/{id}")
    public String eventRemove(@ModelAttribute @PathVariable int id) {
        eventService.removeEvent(id);
        return "redirect:/admin/events";
    }

    @GetMapping("/admin/events/activity/{id}")
    public String activity(@PathVariable int id) {
        eventService.activityEvent(id);
        return "redirect:/admin/events";
    }
}
