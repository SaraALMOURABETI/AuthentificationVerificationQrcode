package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    User findByPassword(String password);

    //User findByEmailandPassword(String email,String password);

    List<UserDto> findAllUsers();

    User findByEmailAndPassword(String email, String password);

    void loginUser(UserDto userDto);
}
