package com.ticketreservation.response;

import com.ticketreservation.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String name;
    private String surname;
    private UserType userType;

}
