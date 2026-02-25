package com.Auth.auth_app.services;

import com.Auth.auth_app.dtos.UserDto;
//Loose coupling concept

public interface UserService  {
  //create user
    UserDto createUser(UserDto userDto);

    //get User by email
    UserDto getUserByEmail (String email);
    //update user
  UserDto updateUser (UserDto userDto, String userId);

    //delete user
  void deleteUser (String userId);

  UserDto getUserById (String userId);

    //get all users
     Iterable<UserDto>getAllUsers();
}
