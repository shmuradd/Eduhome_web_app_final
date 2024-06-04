package org.sb.eduhome2.services.impls;

import org.sb.eduhome2.models.Event;
import org.sb.eduhome2.models.Speaker;
import org.sb.eduhome2.repositories.EventRepository;
import org.sb.eduhome2.repositories.SpeakerRepository;
import org.sb.eduhome2.services.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpeakerServiceImpl implements SpeakerService {
    @Autowired

    private SpeakerRepository speakerRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Speaker> getSpeakersByEventId(int eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId).stream().findFirst();

        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            return event.getSpeakers().stream().collect(Collectors.toList());
        } else {
            throw new RuntimeException("Event not found");
        }
    }
}
