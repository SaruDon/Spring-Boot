package com.example.springsecurity61.controller;

import com.example.springsecurity61.dto.PostDto;
import com.example.springsecurity61.services.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "post")
@RequiredArgsConstructor
public class PostController {

    public final PostService postService;

    @GetMapping
    public List<PostDto> getAllPost(@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "title") String sortBy, @RequestParam(defaultValue = "DESC") String direction){
        return postService.getAllPost(pageNumber,sortBy,direction);
    }

    @GetMapping(path ="/{id}")
    public PostDto getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }


    @PostMapping
    public ResponseEntity<PostDto> createNewPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createNewPost(postDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public PostDto updatePost(@PathVariable Long id, @RequestBody PostDto postDto){
        return postService.updatePost(id,postDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id){

        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public  PostDto partialUpdate(@PathVariable Long id, @RequestBody Map<String, Objects> updates){
        return postService.partialUpdate(id,updates);
    }

}
