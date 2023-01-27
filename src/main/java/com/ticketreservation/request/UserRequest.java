package com.ticketreservation.request;

import com.ticketreservation.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private UserType userType;
}
