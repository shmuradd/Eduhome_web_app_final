package org.sb.eduhome2.services.impls;

import org.modelmapper.ModelMapper;
import org.sb.eduhome2.controllers.PasswordController;
import org.sb.eduhome2.dtos.authdtos.RegisterDto;
import org.sb.eduhome2.dtos.userdtos.UserAddRoleDto;
import org.sb.eduhome2.dtos.userdtos.UserDashboardListDto;
import org.sb.eduhome2.dtos.userdtos.UserDto;
import org.sb.eduhome2.models.Role;
import org.sb.eduhome2.models.UserEntity;
import org.sb.eduhome2.repositories.RoleRepository;
import org.sb.eduhome2.repositories.UserRepository;
import org.sb.eduhome2.services.EmailService;
import org.sb.eduhome2.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(PasswordController.class);


    @Override
    public boolean register(RegisterDto register) {

        UserEntity user = userRepository.findByEmail(register.getEmail());
        if (user != null){
            return false;
        }
        String hashPassword = bCryptPasswordEncoder.encode(register.getPassword());
        String token = bCryptPasswordEncoder.encode(register.getEmail());
        UserEntity newUser = modelMapper.map(register, UserEntity.class);
        newUser.setEmailConfirmed(false);
        newUser.setConfirmationToken(token);
        newUser.setPassword(hashPassword);
        userRepository.save(newUser);
        emailService.sendConfirmationEmail(register.getEmail(),token);
        return true;
    }

    @Override
    public boolean confirmEmail(String email, String token) {

        UserEntity findUser = userRepository.findByEmail(email);
        if (findUser.getConfirmationToken().equals(token) && findUser != null)
        {
            findUser.setEmailConfirmed(true);
            userRepository.saveAndFlush(findUser);
            return true;
        }
        return false;
    }


    @Override
    public List<UserDashboardListDto> getDashboardUsers() {
        List<UserEntity> findUsers = userRepository.findAll();
        List<UserDashboardListDto> users = findUsers.stream().map(user -> modelMapper.map(user, UserDashboardListDto.class)).collect(Collectors.toList());
        return users;
    }

    @Override
    public UserDto getUserById(Long id)
    {
        UserEntity findUser = userRepository.findById(id).orElseThrow();
        UserDto user = modelMapper.map(findUser, UserDto.class);
        return user;
    }

    @Override
    public void addRole(UserAddRoleDto userAddRole) {
        UserEntity findUser = userRepository.findByEmail(userAddRole.getEmail());
        List<Role> roles = roleRepository.findAll().stream().filter(x->x.getId() == userAddRole.getRoleId()).collect(Collectors.toList());
        findUser.setRoles(roles);
        userRepository.save(findUser);

    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void savePasswordResetToken(String email, String token) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            user.setConfirmationToken(token);
            userRepository.save(user);
        }
    }

    @Override
    public boolean verifyToken(String token) {
        UserEntity user = userRepository.findByConfirmationToken(token);
        return user != null;
    }



    @Override
    public void updatePassword(String token, String password) {
        logger.debug("Updating password for token: {}", token);
        UserEntity user = userRepository.findByConfirmationToken(token);
        if (user != null) {
            logger.debug("User found with token: {}", token);
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setConfirmationToken(null); // Clear the reset token after successful reset
            userRepository.save(user);
            logger.debug("Password updated successfully for user: {}", user.getEmail());
        } else {
            logger.debug("No user found with token: {}", token);
        }
    }

    @Override
    public boolean checkPassword(UserEntity user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
