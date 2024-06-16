package org.sb.eduhome2.dtos.event;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class EventCreateDto {

    private String name;
    private String image;
    private LocalDateTime eventDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private String description;
    private String replyText;
    private int speakerId;

}
