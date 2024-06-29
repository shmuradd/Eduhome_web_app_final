package org.sb.eduhome2.services;

import org.sb.eduhome2.dtos.course.CourseCreateDto;
import org.sb.eduhome2.dtos.course.CourseUpdateDto;
import org.sb.eduhome2.dtos.event.EventCreateDto;
import org.sb.eduhome2.dtos.event.EventDetailDto;
import org.sb.eduhome2.dtos.event.EventDto;
import org.sb.eduhome2.dtos.event.EventUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface EventService {

    public Page<EventDto> getEvents(PageRequest pageRequest);

    List<EventDto> getHomeEvents();

    EventDetailDto eventDetail(int id);

    void addEvent(EventCreateDto eventCreateDto);

    EventUpdateDto findUpdateEvent(int id);
    public void updateEvent(int id, EventUpdateDto eventUpdateDto);
    public void removeEvent(int eventId);
    public void activityEvent(int eventId);
}
