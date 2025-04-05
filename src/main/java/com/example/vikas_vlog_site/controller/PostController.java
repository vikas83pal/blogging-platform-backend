package com.example.vikas_vlog_site.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vikas_vlog_site.AppConstants.constants;
import com.example.vikas_vlog_site.payload.ApiResponse;
import com.example.vikas_vlog_site.payload.PostDto;
import com.example.vikas_vlog_site.payload.postResponse;
import com.example.vikas_vlog_site.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/posts")
public class PostController {


    @Autowired
    private PostService postService;
    
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto
                                            , @PathVariable Integer userId
                                            , @PathVariable Integer categoryId){
                                                
        PostDto postDto2 = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(postDto2,HttpStatus.CREATED);
    }

    //get posts by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> postDtos = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    //get posts by category
    @GetMapping("/category/{catagoryId}/posts")
    public ResponseEntity<List<PostDto>> getpostByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtos = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<postResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = constants.PAGE_NUMBER, required = false) Integer pageNumber,
    @RequestParam(value = "pageSize", defaultValue = constants.PAGE_SIZE, required = false) Integer pageSize,
    @RequestParam(value = "sortBy", defaultValue = constants.SORT_By, required = false) String sortBy){
        postResponse pResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy);
        return new ResponseEntity<>(pResponse, HttpStatus.OK);
    }

    //get post by id
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto = this.postService.getpostById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    //delete post by id
    @DeleteMapping("/post/{postId}")
    public ApiResponse deletePostById(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post id deleted", true);
    }

    //update 
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @RequestParam Integer postId, @RequestParam Integer userId, @RequestParam Integer categoryId){
        PostDto postDto2 = this.postService.updatePost(postDto, postId, userId, categoryId);
        return new ResponseEntity<>(postDto2, HttpStatus.OK);
    }    

    

}
