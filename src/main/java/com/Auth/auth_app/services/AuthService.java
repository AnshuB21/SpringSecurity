package com.Auth.auth_app.services;


import com.Auth.auth_app.dtos.UserDto;

public interface AuthService {
    UserDto registerUser(UserDto userDto);
}
