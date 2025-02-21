package com.example.vikas_vlog_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vikas_vlog_site.payload.userDto;
import com.example.vikas_vlog_site.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/user")
public class userController {

    @Autowired
    private UserService userService;

   
    
    @PostMapping("/")
    public ResponseEntity<userDto> createUser(@RequestBody userDto userDto){
        userDto userDto2 =  this.userService.createUser(userDto);
        return new ResponseEntity<>(userDto2, HttpStatus.CREATED);
    }

    

}
