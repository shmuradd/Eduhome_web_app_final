package org.sb.eduhome2.dtos.userdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddRoleDto {
    private String email;
    private Long roleId;
}