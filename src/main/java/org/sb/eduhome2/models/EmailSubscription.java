package org.sb.eduhome2.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="subscriptions")
@Data
public class EmailSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
}
