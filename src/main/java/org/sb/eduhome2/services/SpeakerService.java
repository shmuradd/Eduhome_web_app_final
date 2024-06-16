package org.sb.eduhome2.services;

import org.sb.eduhome2.dtos.speakers.SpeakerCreateDto;
import org.sb.eduhome2.dtos.speakers.SpeakerUpdateDto;
import org.sb.eduhome2.dtos.teachers.TeacherCreateDto;
import org.sb.eduhome2.dtos.teachers.TeacherUpdateDto;
import org.sb.eduhome2.models.Speaker;

import java.util.List;

public interface SpeakerService {
    List<Speaker> getSpeakersByEventId(int eventId);

    //admin
    public void addSpeaker(SpeakerCreateDto speakerCreateDto);

    public SpeakerUpdateDto findUpdateSpeaker(int id);
    public void updateSpeaker(int id, SpeakerUpdateDto speakerUpdateDto);
    public void removeSpeaker(int speakerId);
}
