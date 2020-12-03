package org.example.demo.controller;

import org.example.demo.auth.ApplicationUser;
import org.example.demo.auth.ApplicationUserService;
import org.example.demo.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class UserController {
    @Autowired
    ApplicationUserService applicationUserService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("user")
    public ApplicationUser addApplicationUser(@RequestBody ApplicationUser addedUser){
        ApplicationUser user = new ApplicationUser();
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setUsername(addedUser.getUsername());
        user.setPassword(passwordEncoder.encode(addedUser.getPassword()));
        user.setRole(ApplicationUserRole.STUDENT);
        ApplicationUser user1 = applicationUserService.addUser(user);
        return user1;
    }

    @GetMapping("current_user")
    public ApplicationUser getCurrentUser(Authentication authentication){
        String currentUserName = authentication.getName();
        return (ApplicationUser) applicationUserService.loadUserByUsername(currentUserName);
    }
}
