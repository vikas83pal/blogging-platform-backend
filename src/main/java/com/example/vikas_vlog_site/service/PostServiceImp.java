package com.example.vikas_vlog_site.service;

import com.example.vikas_vlog_site.payload.PostDto;
import com.example.vikas_vlog_site.payload.postResponse;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.vikas_vlog_site.exception.ResourceNotFoundException;
import com.example.vikas_vlog_site.model.Catagory;
import com.example.vikas_vlog_site.model.Post;
import com.example.vikas_vlog_site.model.User;
import com.example.vikas_vlog_site.repository.CategoryRepo;
import com.example.vikas_vlog_site.repository.PostRepo;
import com.example.vikas_vlog_site.repository.UserRepo;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;


        @Override
        public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
            User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("UserId", "id ", userId));
            Catagory cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("categoryId", "Id ", categoryId));
            Post post = this.modelMapper.map(postDto, Post.class);
            post.setImageName("default.png");
            post.setAddedDate(new Date(System.currentTimeMillis()));
            post.setCategory(cat);
            post.setUser(user);
            Post savedPost = this.postRepo.save(post);
            return this.modelMapper.map(savedPost, PostDto.class);
        }        
        
       @Override
        public PostDto updatePost(PostDto postDto, Integer postId, Integer userId, Integer categoryId) {
            User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("UserId", "id ", userId));
            Catagory cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("categoryId", "Id ", categoryId));
            Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
            post.setPostTitle(postDto.getPostTitle());
            post.setContent(postDto.getContent());
            post.setImageName(postDto.getImageName());
            post.setCategory(cat);
            post.setUser(user);
            Post updatedPost = this.postRepo.save(post);
            return this.modelMapper.map(updatedPost, PostDto.class);
        }
        @Override
        public void deletePost(Integer postId) {
            Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
            this.postRepo.delete(post);
        }
    
        @Override
        public postResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {
            Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

            Page<Post> pagePost = this.postRepo.findAll(p);

            List<Post> posts = pagePost.getContent();


            List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

            postResponse pres = new postResponse();
            pres.setContent(postDtos.toString());
            pres.setPageNumber(pagePost.getNumber());
            pres.setPageSize(pagePost.getSize());
            pres.setTotalElement((int) pagePost.getTotalElements());
            pres.setTotalPages(pagePost.getTotalPages());
            pres.setLastPage(pagePost.isLast());

            return pres;
        }
    
        @Override
        public PostDto getpostById(Integer postId) {
            Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId ", postId));
            return this.modelMapper.map(post, PostDto.class);
        }

        @Override
        public List<PostDto> getPostByCategory(Integer categoryId){
            Catagory cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId ", categoryId));

           List<Post> posts = this.postRepo.findByCategory(cat);

            List<PostDto> postsDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
            
            return postsDtos;
        }

        @Override
        public List<PostDto> getPostByUser(Integer userId){
            User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId ", userId));

            List<Post> posts = this.postRepo.findByUser(user);

            List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());

            return postDtos;
        }

    }
