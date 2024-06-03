package org.sb.eduhome2.dtos.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {
    private int id;
    private String name;
    private String image;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;

}
