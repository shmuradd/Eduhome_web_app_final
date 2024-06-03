package org.sb.eduhome2.services;

import org.sb.eduhome2.models.Speaker;

import java.util.List;

public interface SpeakerService {
    List<Speaker> getSpeakersByEventId(int eventId);
}
