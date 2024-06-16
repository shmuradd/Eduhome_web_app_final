package org.sb.eduhome2.dtos.speakers;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SpeakerUpdateDto {
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
}
