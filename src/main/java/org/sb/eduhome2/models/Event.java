package org.sb.eduhome2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String image;
    private LocalDateTime eventDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    @Column(nullable = false) // Make isDeleted persistent
    private boolean isDeleted;

    private String description;
    private String replyText;

    @ManyToMany(mappedBy = "events")
    private List<Speaker> speakers = new ArrayList<>();


    public void addSpeaker(Speaker speaker)
    {
        this.speakers.add(speaker);
        speaker.getEvents().add(this);
    }

    public void clearSpeakers() {
        for (Speaker speaker : this.speakers) {
            speaker.getEvents().remove(this);
        }
        this.speakers.clear();
    }

    public void removeDuplicateSpeakers() {
        Set<Integer> speakerIds = new HashSet<>();
        List<Speaker> uniqueSpeakers = new ArrayList<>();
        for (Speaker speaker : this.speakers) {
            if (speakerIds.add(speaker.getId())) {
                uniqueSpeakers.add(speaker);
            } else {
                speaker.getEvents().remove(this);
            }
        }
        this.speakers = uniqueSpeakers;
    }


}
