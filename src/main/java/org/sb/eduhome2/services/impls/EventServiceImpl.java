package org.sb.eduhome2.services.impls;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.dtos.course.CourseUpdateDto;
import org.sb.eduhome2.dtos.event.EventCreateDto;
import org.sb.eduhome2.dtos.event.EventDetailDto;
import org.sb.eduhome2.dtos.event.EventDto;
import org.sb.eduhome2.dtos.event.EventUpdateDto;
import org.sb.eduhome2.models.Course;
import org.sb.eduhome2.models.Event;
import org.sb.eduhome2.models.Speaker;
import org.sb.eduhome2.repositories.EventRepository;
import org.sb.eduhome2.repositories.SpeakerRepository;
import org.sb.eduhome2.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SpeakerRepository speakerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<EventDto> getEvents() {
        List<EventDto> eventDtoList=eventRepository.findAll().stream()
                .filter(x->x.isDeleted()==false)
                .map(event->modelMapper.map(event, EventDto.class))
                .collect(Collectors.toList());
        return eventDtoList;
    }


    @Override
    public List<EventDto> getHomeEvents() {
        List<EventDto> eventDtoList=eventRepository.findAll().stream()
                .filter(x->x.isDeleted()==false)
                .map(event->modelMapper.map(event, EventDto.class))
                .limit(4)
                .collect(Collectors.toList());
        return eventDtoList;
    }


    @Override
    public EventDetailDto eventDetail(int id) {
        Event event = eventRepository.findById(id).orElse(null);
        EventDetailDto eventDetailDto = modelMapper.map(event,EventDetailDto.class);
        return eventDetailDto;
    }

    @Override
    public void addEvent(EventCreateDto eventCreateDto) {
        try {
            Event event = new Event();
            event.setName(eventCreateDto.getName());
            event.setImage(eventCreateDto.getImage());
            event.setEventDate(eventCreateDto.getEventDate());
            event.setStartTime(eventCreateDto.getStartTime());
            event.setEndTime(eventCreateDto.getEndTime());
            event.setLocation(eventCreateDto.getLocation());
            event.setDescription(eventCreateDto.getDescription());
            event.setReplyText(eventCreateDto.getReplyText());
            Set<Speaker> speakers = eventCreateDto.getSpeakerId().stream()
                    .map(id -> speakerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Speaker not found with ID: " + id)))
                    .collect(Collectors.toSet());
            event.setSpeakers(speakers);            // Add any additional fields here if needed
            event.setDeleted(false);
            eventRepository.save(event);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public EventUpdateDto findUpdateEvent(int id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event not found with id " + id));
        return modelMapper.map(event, EventUpdateDto.class);
    }

    @Override
    @Transactional
    public void updateEvent(int id, EventUpdateDto eventUpdateDto) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event not found with id " + id));
        event.setName(eventUpdateDto.getName());
        event.setImage(eventUpdateDto.getImage());
        event.setEventDate(eventUpdateDto.getEventDate());
        event.setStartTime(eventUpdateDto.getStartTime());
        event.setEndTime(eventUpdateDto.getEndTime());
        event.setLocation(eventUpdateDto.getLocation());
        event.setDescription(eventUpdateDto.getDescription());
        event.setReplyText(eventUpdateDto.getReplyText());

        eventRepository.saveAndFlush(event);
    }


    @Override
    @Transactional
    public void removeEvent(int eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event not found with id " + eventId));
        event.setDeleted(true);
        eventRepository.flush();
    }

    @Override
    @Transactional
    public void activityEvent(int eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event not found with id " + eventId));
        event.setDeleted(!event.isDeleted()); // Toggle the deleted status
        eventRepository.flush();
    }


}
