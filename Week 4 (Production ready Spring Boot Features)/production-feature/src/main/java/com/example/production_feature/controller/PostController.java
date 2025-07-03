package com.example.production_feature.controller;

import com.example.production_feature.dto.PostDto;
import com.example.production_feature.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPost(){
        return ResponseEntity.ok(postService.getAllPost());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<PostDto> createNewPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createNewPost(postDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostDto> partialUpdatePost(@RequestBody Map<String,Object>updates, @PathVariable Long id){
        return ResponseEntity.ok(postService.partialUpdatePost(updates,id));
    }
}
