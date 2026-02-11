package com.example.springsecurity61.services;

import com.example.springsecurity61.dto.PostDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface PostService {
    List<PostDto> getAllPost(Integer pageNo, String sortBy ,String direction);

    PostDto createNewPost(PostDto postDto);

    PostDto updatePost(Long id, PostDto postDto);

    void deletePostById(Long id);

    PostDto getPostById(Long id);

    PostDto partialUpdate(Long id,Map<String, Objects> updates);
}
