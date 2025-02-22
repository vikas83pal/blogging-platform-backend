package com.example.vikas_vlog_site.service;

import java.util.List;


import com.example.vikas_vlog_site.payload.userDto;


public interface UserService {
    
    userDto createUser(userDto user);

    userDto updateUser(userDto user,Integer userId);

    userDto getUserById(Integer userId);

    List<userDto> getAllUsers();

    userDto deleteUser(Integer userId);

    void deleteAllUser();
}
