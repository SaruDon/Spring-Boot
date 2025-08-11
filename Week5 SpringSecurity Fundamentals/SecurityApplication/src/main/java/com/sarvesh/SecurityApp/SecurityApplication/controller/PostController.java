package com.sarvesh.SecurityApp.SecurityApplication.controller;


import com.sarvesh.SecurityApp.SecurityApplication.dto.PostDto;
import com.sarvesh.SecurityApp.SecurityApplication.repository.PostRepository;
import com.sarvesh.SecurityApp.SecurityApplication.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private  final PostService postService;

    @GetMapping
    private ResponseEntity<List<PostDto>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{postId}")
    private ResponseEntity<PostDto> getPostById(@PathVariable Long postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PostMapping
    private ResponseEntity<PostDto> createNewPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createNewPost(postDto), HttpStatus.CREATED);
    }
}
