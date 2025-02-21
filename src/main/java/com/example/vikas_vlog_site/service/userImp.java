package com.example.vikas_vlog_site.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vikas_vlog_site.exception.ResourceNotFoundException;
import com.example.vikas_vlog_site.model.User;
import com.example.vikas_vlog_site.payload.userDto;
import com.example.vikas_vlog_site.repository.UserRepo;

@Service
public class userImp implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public userDto createUser(userDto userDto) {
        User newUser = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(newUser);
        return this.userTodto(savedUser);
    }

    @Override
    public userDto updateUser(userDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setId(userDto.getId());

        User updateUser = this.userRepo.save(user);
        userDto userDto2 = this.userTodto(updateUser);
        return userDto2;

    }

    @Override
    public userDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        return this.userTodto(user);
    }

    @Override
    public List<userDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<userDto> userDtos = users.stream().map(user -> userTodto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        this.userRepo.delete(user);

    }


    private User dtoToUser(userDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        return user;
    }
    

    private userDto userTodto(User user){
        userDto userdto = new userDto();
        userdto.setId(user.getId());
        userdto.setName(user.getName());
        userdto.setEmail(user.getEmail());
        userdto.setPassword(user.getPassword());
        userdto.setAbout(user.getAbout());
        return userdto;
    }
}
