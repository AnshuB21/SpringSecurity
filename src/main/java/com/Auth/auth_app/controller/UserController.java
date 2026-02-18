package com.Auth.auth_app.controller;


import com.Auth.auth_app.dtos.UserDto;
import com.Auth.auth_app.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
//@AllArgsConstructor
public class UserController {
       private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping()
    public ResponseEntity<UserDto> createUser (@RequestBody UserDto userDto){
           return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @GetMapping()
    public ResponseEntity<Iterable<UserDto>> getAllUsers (){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

}
