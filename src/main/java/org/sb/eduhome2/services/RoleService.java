package org.sb.eduhome2.services;

import org.sb.eduhome2.dtos.roledtos.RoleDto;
import org.sb.eduhome2.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDto> getRoles();
    public List<Role> findAllRoles();
    public Optional<Role> findById(Long id);

}
