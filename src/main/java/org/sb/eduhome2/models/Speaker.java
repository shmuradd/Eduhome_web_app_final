package org.sb.eduhome2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="speakers")
@Data

public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 200)
    private String fullname;

    @NotBlank
    @Size(max = 300)
    private String image;

    @NotBlank
    @Size(max = 200)
    private String duty;

    @NotBlank
    @Size(max = 300)
    private String company;

    @ManyToMany(cascade = { CascadeType.ALL })
  //  @JoinTable(name = "event_speakers", joinColumns = @JoinColumn(name = "speakers", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "events",referencedColumnName = "id"))
    @JoinTable(
            name = "event_speakers",
            joinColumns = { @JoinColumn(name = "speaker_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "event_id", referencedColumnName = "id") }
    )
    private Set<Event> events =new HashSet<>();

}

