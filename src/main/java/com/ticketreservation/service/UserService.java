package com.ticketreservation.service;

import com.ticketreservation.config.RabbitMQConfiguration;
import com.ticketreservation.controller.UserController;
import com.ticketreservation.model.User;
import com.ticketreservation.repository.UserRepository;
import com.ticketreservation.request.LoginRequest;
import com.ticketreservation.request.UserRequest;
import com.ticketreservation.request.UserUpdateRequest;
import com.ticketreservation.response.UserResponse;
import com.ticketreservation.converter.UserConverter;
import com.ticketreservation.util.PasswordUtil;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.ticketreservation.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class UserService {

    private static final String EMAIL_OR_PASSWORD_WRONG="Your email or password wrong";
    private static final String LOGIN_SUCCESS="Login successful";

    private UserRepository userRepository;
    private UserConverter userConverter;
    private RabbitMQConfiguration rabbitMQConfiguration;
    private RabbitTemplate rabbitTemplate;


    public UserService( UserRepository userRepository, UserConverter userConverter)
    {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.rabbitMQConfiguration = rabbitMQConfiguration;
        this.rabbitTemplate = rabbitTemplate;
    }


    public UserResponse createUser(UserRequest userRequest){
        Logger logger = Logger.getLogger(UserController.class.getName());

        String hash = PasswordUtil.preparePasswordHash(userRequest.getPassword());
        logger.log(Level.INFO,"[[createUser] - password hash created: {0}]",hash);

        User savedUser = userRepository.save(userConverter.convert(userRequest,hash));
        logger.log(Level.INFO, "[createUser] - user created: {0}", savedUser.getId());

        rabbitTemplate.convertAndSend(rabbitMQConfiguration.getQueueName(), userRequest);
        logger.log(Level.WARNING, "[createUser] - userRequest: {0}, sent to : {1}",
                new Object[] { userRequest.getEmail(), rabbitMQConfiguration.getQueueName() });

        rabbitTemplate.convertAndSend(userRepository.findByEmail(savedUser.getEmail()));

        return userConverter.convert(savedUser);
    }
    public UserResponse update(UserUpdateRequest userUpdateRequest) {

        return null;
    }

    public String login(LoginRequest loginRequest) {

        User foundUser = userRepository.findByEmail(loginRequest.getEmail());

        if(!foundUser.equals(loginRequest)){
            throw new UserNotFoundException("User not found");
        }

        String passwordHash = PasswordUtil.preparePasswordHash(loginRequest.getPassword());

        boolean isValid = PasswordUtil.validatePassword(passwordHash, foundUser.getPassword());

        return isValid ? LOGIN_SUCCESS : EMAIL_OR_PASSWORD_WRONG;
    }

    public Optional<User> getById(Integer userId) {
        return userRepository.findById(userId);
    }
}
