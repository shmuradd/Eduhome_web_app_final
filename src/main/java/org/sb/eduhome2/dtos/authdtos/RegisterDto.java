package org.sb.eduhome2.dtos.authdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String passwordRepeat;
}
