package com.example.vikas_vlog_site.service;

import java.util.List;

import com.example.vikas_vlog_site.payload.PostDto;
import com.example.vikas_vlog_site.payload.postResponse;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId, Integer userId, Integer categoryId);

    PostDto getpostById(Integer postId);

    postResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);

    void deletePost(Integer postId);


    List<PostDto> getPostByCategory(Integer catagoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyword);


    

}
