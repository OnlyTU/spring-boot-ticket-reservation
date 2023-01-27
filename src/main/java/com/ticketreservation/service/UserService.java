package com.ticketreservation.service;

import com.ticketreservation.model.User;
import com.ticketreservation.repository.UserRepository;
import com.ticketreservation.request.UserRequest;
import com.ticketreservation.request.UserUpdateRequest;
import com.ticketreservation.response.UserResponse;
import com.ticketreservation.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserConverter userConverter;

    public UserService( UserRepository userRepository, UserConverter userConverter)
    {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }


    public UserResponse createUser(UserRequest userRequest){
        User savedUser = userRepository.save(userConverter.convert(userRequest));
        return userConverter.convert(savedUser);
    }
    public UserResponse update(UserUpdateRequest userUpdateRequest) {
        return null;
    }

    //login
}
