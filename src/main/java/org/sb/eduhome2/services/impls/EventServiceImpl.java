package org.sb.eduhome2.services.impls;
import org.modelmapper.ModelMapper;
import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.dtos.event.EventDetailDto;
import org.sb.eduhome2.dtos.event.EventDto;
import org.sb.eduhome2.models.Course;
import org.sb.eduhome2.models.Event;
import org.sb.eduhome2.repositories.EventRepository;
import org.sb.eduhome2.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

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


}
