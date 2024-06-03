package org.sb.eduhome2.services;

import org.sb.eduhome2.dtos.event.EventDetailDto;
import org.sb.eduhome2.dtos.event.EventDto;

import java.util.List;

public interface EventService {

    List<EventDto> getEvents();

    List<EventDto> getHomeEvents();

    EventDetailDto eventDetail(int id);
}
