package com.ifennanwafor.week7task1.dto;


import com.ifennanwafor.week7task1.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;
}

//    public UserDto(User user) {
//        this.id = user.getId();
//        this.email = user.getEmail();
//        this.password = user.getPassword();
//        this.firstName = user.getFirstName();
//        this.lastName = user.getLastName();
//
//    }



