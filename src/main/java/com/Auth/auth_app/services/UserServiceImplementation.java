package com.Auth.auth_app.services;

import com.Auth.auth_app.Respository.UserRepository;
import com.Auth.auth_app.dtos.UserDto;
import com.Auth.auth_app.entity.Provider;
import com.Auth.auth_app.entity.User;
import com.Auth.auth_app.exceptions.ResourceNotFoundException;
import com.Auth.auth_app.helpers.UserHelper;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService{


    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Transactional
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
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public UserDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }

        return modelMapper.map(user, UserDto.class);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

        UUID uid = UserHelper.parseUUID(userId);

        User user = userRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with id: " + userId));

        // Update only allowed fields
        if (userDto.getName() != null) user.setName(userDto.getName());
        if (userDto.getImage() != null) user.setImage(userDto.getImage());
        user.setEnable(userDto.isEnable());

        if (userDto.getProvider() != null) user.setProvider(userDto.getProvider());

        // Usually you don't accept createdAt from client
        user.setUpdatedAt(Instant.now());

        // Password update should be special (encode it)
        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
            user.setPassword(userDto.getPassword());
        }

        // Roles update: only if your API allows changing role
        // user.setRoles(mappedRoles);

        User saved = userRepository.save(user);

        return modelMapper.map(saved, UserDto.class);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    @Transactional
    public void deleteUser(String userId) {
        UUID uId=UserHelper.parseUUID(userId);
        User user= userRepository.findById(uId).orElseThrow(()-> new ResourceNotFoundException("User with this Id doesn't exists"));
        userRepository.deleteById(uId);
        //userRepository.delete(user)
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public UserDto getUserById(String userId) {
        UUID uid = UserHelper.parseUUID(userId);

        User user = userRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with id: " + userId));
        return modelMapper.map(user, UserDto.class);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////
   // @Transactional(Readonly == true)
    @Override
    public Iterable<UserDto> getAllUsers() {
        if (userRepository.count() <= 0) {
            throw new IllegalStateException("No user present");
        }

        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

}
