package org.sb.eduhome2.services.impls;

import org.modelmapper.ModelMapper;
import org.sb.eduhome2.dtos.roledtos.RoleDto;
import org.sb.eduhome2.models.Role;
import org.sb.eduhome2.repositories.RoleRepository;
import org.sb.eduhome2.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RoleDto> getRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> result=roles.stream().map(role -> modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
        return result;
    }
    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }
}
