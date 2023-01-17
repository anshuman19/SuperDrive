package com.udacity.jwdnd.spring_security_basics.service;

import com.udacity.jwdnd.spring_security_basics.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private UserService userService;

    public AuthorizationService(UserService userService) {
        this.userService = userService;
    }

    public boolean signupUser(User user) {

        String username = user.getUsername();

        if (!this.userService.isUsernameAvailable(username)) {
            return false;
        }

        this.userService.createUser(user);

        return true;
    }
}