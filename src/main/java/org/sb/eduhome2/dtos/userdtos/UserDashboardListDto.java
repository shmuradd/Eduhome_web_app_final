package org.sb.eduhome2.dtos.userdtos;

import lombok.Getter;
import lombok.Setter;
import org.sb.eduhome2.models.Role;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDashboardListDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean emailConfirmed;
    private List<Role> roles = new ArrayList<>();
}
