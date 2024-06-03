package org.sb.eduhome2.dtos.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDetailDto {private int id;
    private String name;
    private String image;
    private LocalDateTime eventDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;

    private String description;
    private String replyText;


}
