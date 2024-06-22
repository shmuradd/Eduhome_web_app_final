package org.sb.eduhome2.services;

import org.sb.eduhome2.dtos.authdtos.RegisterDto;
import org.sb.eduhome2.dtos.userdtos.UserAddRoleDto;
import org.sb.eduhome2.dtos.userdtos.UserDashboardListDto;
import org.sb.eduhome2.dtos.userdtos.UserDto;
import org.sb.eduhome2.models.UserEntity;

import java.util.List;

public interface UserService {
    boolean register(RegisterDto register);
    boolean confirmEmail(String email, String token);
    List<UserDashboardListDto> getDashboardUsers();
    UserDto getUserById(Long id);
    void addRole(UserAddRoleDto userAddRole);
    public UserEntity findUserByEmail(String email);
    public void savePasswordResetToken(String email, String token);
    public boolean verifyToken(String token);
    public void updatePassword(String token, String password);
    boolean checkPassword(UserEntity user, String rawPassword);

}
