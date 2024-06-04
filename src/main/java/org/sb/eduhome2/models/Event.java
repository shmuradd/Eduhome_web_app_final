package org.sb.eduhome2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
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
    //@JoinTable(name = "event_speakers", joinColumns = @JoinColumn(name = "events", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "speakers",referencedColumnName = "id"))
    private Set<Speaker> speakers =new HashSet<>();



}
