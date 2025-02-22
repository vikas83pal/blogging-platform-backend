package com.example.vikas_vlog_site.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public userDto createUser(userDto userDto) {
        User newUser = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(newUser);
        return this.userTodto(savedUser);
    }

    @Override
    public userDto updateUser(userDto userDto, Integer userId) {
        // Fetch user or throw exception if not found
        User user = this.userRepo.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    
        // Update fields (excluding ID)
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
    
        // Save updated user
        User updatedUser = this.userRepo.save(user);
    
        // Convert entity to DTO
        return this.userTodto(updatedUser);
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
    public userDto deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        this.userRepo.delete(user);
        return this.userTodto(user);

    }

    @Override
    public void deleteAllUser(){
        this.userRepo.deleteAll();
    }


    private User dtoToUser(userDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }
    

    private userDto userTodto(User user){
        userDto userdto = this.modelMapper.map(user, userDto.class);
        return userdto;
    }
}
