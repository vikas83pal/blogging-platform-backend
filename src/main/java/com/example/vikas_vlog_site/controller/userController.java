package com.example.vikas_vlog_site.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vikas_vlog_site.payload.userDto;
import com.example.vikas_vlog_site.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/user")
public class userController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<userDto> createUser(@Valid @RequestBody userDto userDto) {
        userDto userDto2 = this.userService.createUser(userDto);
        return new ResponseEntity<>(userDto2, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<userDto> getUserById(@PathVariable Integer id) {
        userDto user = this.userService.getUserById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByUserId(@PathVariable int id) {
        userDto user = this.userService.deleteUser(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", id + " user is deleted");
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<userDto>> getAllUser() {
        List<userDto> users = this.userService.getAllUsers();
        if (users == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<?> deleteAllUsers() {
        this.userService.deleteAllUser();
        Map<String, String> response = new HashMap<>();
        response.put("message", "All users are deleted");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<userDto> updateUser(@Valid @RequestBody userDto userDto, @PathVariable int id) {
        userDto updatedUser = this.userService.updateUser(userDto, id);

        if (updatedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(updatedUser);
    }

}
