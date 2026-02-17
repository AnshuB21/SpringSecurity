package com.Auth.auth_app.services;

import com.Auth.auth_app.Respository.UserRepository;
import com.Auth.auth_app.dtos.UserDto;
import com.Auth.auth_app.entity.Provider;
import com.Auth.auth_app.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        if(userDto.getEmail()==null || userDto.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new IllegalArgumentException("Already exists user with email");
        }

        //Convert Dto to your entity for saving in DB
        User user= modelMapper.map(userDto, User.class);
        user.setProvider(userDto.getProvider()!=null? userDto.getProvider(): Provider.LOCAL);

        //role assign here to user for authorization

        //Finally saving the entity
       User savedUser= userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDto getUserByEMail(String email) {
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public UserDto getUserById(String userId) {
        return null;
    }

    @Override
    public Iterable<UserDto> getAllUsers() {
        return null;
    }
}
