package com.example.vikas_vlog_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vikas_vlog_site.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
    
}
