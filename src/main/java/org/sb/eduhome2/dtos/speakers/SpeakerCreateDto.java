package org.sb.eduhome2.dtos.speakers;

import jakarta.persistence.*;
import lombok.Data;
import org.sb.eduhome2.models.Event;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class SpeakerCreateDto {
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
}
