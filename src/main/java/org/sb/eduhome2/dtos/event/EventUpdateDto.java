package org.sb.eduhome2.dtos.event;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventUpdateDto {
    private int id;
    private String name;
    private String image;
    private LocalDateTime eventDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;

    private int speakerId;

    private String description;
    private String replyText;
}
