package com.ticketreservation.converter;

import com.ticketreservation.model.User;
import com.ticketreservation.request.UserRequest;
import com.ticketreservation.response.UserResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    public UserResponse convert(User user){
        UserResponse response = new UserResponse();
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setUserType(user.getUserType());
        return response;
    }

    public User convert(UserRequest userRequest,String hash){
        User user = new User();
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setPassword(hash);
        user.setEmail(userRequest.getEmail());
        user.setCreateDate(LocalDateTime.now());
        user.setUserType(userRequest.getUserType());
        return user;
    }

}
