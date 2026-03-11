package com.Auth.auth_app.services.Impl;

import com.Auth.auth_app.dtos.UserDto;
import com.Auth.auth_app.services.AuthService;
import com.Auth.auth_app.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    @Override
    public UserDto registerUser(UserDto userDto){
        //logic for strong password or username
        //default roles
        UserDto userDto1 = userService.createUser(userDto);
        return userDto1;
    }
}
