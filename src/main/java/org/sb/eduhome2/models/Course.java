package org.sb.eduhome2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String image;
    @Column(nullable = false)
    private boolean isDeleted=false;

    private String applyDescription;
    private String certificationDescription;
    private LocalDate startDate;
    private String durationTime;
    private String classDuration;
    private String skillLevel;
    private String language;
    private int studentCapacity;
    private String assessments;
    private double price;



}
