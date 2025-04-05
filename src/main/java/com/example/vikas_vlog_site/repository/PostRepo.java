package com.example.vikas_vlog_site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vikas_vlog_site.model.Catagory;
import com.example.vikas_vlog_site.model.Post;
import com.example.vikas_vlog_site.model.User;
public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Catagory category);  
}
