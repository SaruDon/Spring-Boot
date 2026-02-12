package com.example.SpringSecurity63.service;

import com.example.SpringSecurity63.dto.PostDto;

import java.util.List;
import java.util.Map;
import java.util.Objects;


public interface PostService {

    List<PostDto> getAllPost(Integer pageNumber, String sortBy, String direction);

    PostDto createNewPost(PostDto postDto);

    PostDto updatePost(Long id, PostDto postDto);

    void deletePostById(Long id);

    PostDto getPostById(Long id);

    PostDto partialUpdate(Long id, Map<String, Objects> updates);
}