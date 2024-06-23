package org.sb.eduhome2.controllers;

import org.sb.eduhome2.models.Role;
import org.sb.eduhome2.models.UserEntity;
import org.sb.eduhome2.services.RoleService;
import org.sb.eduhome2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class UserManagementController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping("/admin/users")
    public String showUserList(Model model) {
        List<UserEntity> users = userService.findAllUsers();
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "dashboard/user-management";
    }

    @PostMapping("/admin/users/update-role")
    public String updateUserRole(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {
        Optional<UserEntity> userOptional = userService.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            Role role = roleService.findById(roleId).orElse(null);
            if (role != null) {
                user.getRoles().clear();
                user.getRoles().add(role);
                userService.save(user);
            }
        }
        return "redirect:/admin/users";
    }
}
