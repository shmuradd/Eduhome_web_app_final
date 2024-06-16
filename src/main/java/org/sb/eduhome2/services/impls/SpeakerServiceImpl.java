package org.sb.eduhome2.services.impls;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.sb.eduhome2.dtos.speakers.SpeakerCreateDto;
import org.sb.eduhome2.dtos.speakers.SpeakerUpdateDto;
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


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addSpeaker(SpeakerCreateDto speakerCreateDto) {
        Speaker speaker = modelMapper.map(speakerCreateDto, Speaker.class);
        speakerRepository.save(speaker);
    }

    @Override
    public SpeakerUpdateDto findUpdateSpeaker(int id) {
        Speaker speaker = speakerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Speaker not found with id " + id));
        return modelMapper.map(speaker, SpeakerUpdateDto.class);
    }

    @Override
    @Transactional
    public void updateSpeaker(int id, SpeakerUpdateDto speakerUpdateDto) {
        Speaker speaker = speakerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Speaker not found with id " + id));
        modelMapper.map(speakerUpdateDto, speaker);
        speakerRepository.saveAndFlush(speaker);
    }

    @Override
    @Transactional
    public void removeSpeaker(int speakerId) {
        Speaker speaker = speakerRepository.findById(speakerId)
                .orElseThrow(() -> new EntityNotFoundException("Speaker not found with id " + speakerId));
        speakerRepository.delete(speaker);
    }



}
