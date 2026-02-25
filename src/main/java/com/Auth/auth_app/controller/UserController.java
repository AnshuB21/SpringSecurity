package com.Auth.auth_app.controller;


import com.Auth.auth_app.Respository.UserRepository;
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
    private final UserRepository userRepository;

    //Manually created the constructor because the lombok was throwing error
    public UserController(UserService userService,
                          UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    //Method to post user into the table
    @PostMapping()
    /*  @RequestBody is used to:
        Take JSON data from the HTTP request body
        Convert it into a Java object*/
    public ResponseEntity<UserDto> createUser (@RequestBody UserDto userDto){
           return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    //Method to get all the users from the database
    @GetMapping()
    public ResponseEntity<Iterable<UserDto>> getAllUsers (){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    //Method to get a user with given email id
    @GetMapping("/getByEmail/{email}")
    //@PathVariable is used to extract values from the URL path. {email}
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.getUserByEmail(email));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId")String userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser (@RequestBody UserDto userDto, @PathVariable String userId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateUser(userDto,userId));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser (@PathVariable String userId){
        userService.deleteUser(userId);
    }
}
