package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.dtos.event.EventDetailDto;
import org.sb.eduhome2.dtos.event.EventDto;
import org.sb.eduhome2.models.Speaker;
import org.sb.eduhome2.services.EventService;
import org.sb.eduhome2.services.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class EventsController {

    @Autowired
    private EventService eventService;

    @Autowired
    private SpeakerService speakerService;
    @GetMapping("/events")
    public String index(Model model){
    List<EventDto> events= eventService.getEvents();
        model.addAttribute("events", events);
        return "event/events";
    }



    @GetMapping("/events/{id}")
    public String detail(@PathVariable int id, Model model)
    {
        EventDetailDto eventDetailDto=eventService.eventDetail(id);
        List<Speaker> speakers=speakerService.getSpeakersByEventId(id);
        model.addAttribute("event", eventDetailDto);
        model.addAttribute("speakers",speakers);
        return "event/event-detail";
    }
}
