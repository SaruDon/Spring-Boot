package com.product_ready_features.product_ready_features.controllers;


import com.product_ready_features.product_ready_features.dto.PostDto;
import com.product_ready_features.product_ready_features.repository.PostRepository;
import com.product_ready_features.product_ready_features.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "post")
@RequiredArgsConstructor
public class PostController {

     final private  PostService postService;


    @GetMapping
    public List<PostDto> getAllPost(){
        return postService.getAllPost();
    }


    @GetMapping(path = "/{postId}")
    public PostDto getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDto createPost(@RequestBody PostDto postDto){
        return postService.createPost(postDto);
    }

    @PutMapping(path = "/{postId}")
    public PostDto updatePost(@RequestBody PostDto postDto,@PathVariable Long postId){
        return postService.updatePostById(postDto,postId);
    }

}
