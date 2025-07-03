package com.example.production_feature.service;

import com.example.production_feature.dto.PostDto;

import java.util.List;
import java.util.Map;

public interface PostService {
    List<PostDto> getAllPost();

    PostDto createNewPost(PostDto postDto);

    PostDto getPostById(Long id);

    PostDto partialUpdatePost(Map<String, Object> updates, Long id);
}
