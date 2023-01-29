package com.ticketreservation.controller;


import com.ticketreservation.request.LoginRequest;
import com.ticketreservation.request.UserRequest;
import com.ticketreservation.request.UserUpdateRequest;
import com.ticketreservation.response.UserResponse;
import com.ticketreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }
    @PutMapping
    public ResponseEntity<UserResponse> update(@RequestBody UserUpdateRequest userUpdateRequest){
        UserResponse userResponse =userService.update(userUpdateRequest);
        return ResponseEntity.ok(userResponse);
    }
    @PostMapping(value = "/api/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }


}
