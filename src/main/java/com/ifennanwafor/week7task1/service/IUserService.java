package com.ifennanwafor.week7task1.service;


import com.ifennanwafor.week7task1.dto.UserDto;
import com.ifennanwafor.week7task1.models.User;

public interface IUserService {
    UserDto saveUser(UserDto userDTO);
    User loginUser(UserDto userDTO);
    User findByEmail(String email);
}
