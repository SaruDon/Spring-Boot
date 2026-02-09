package com.example.SpringSecurity54.services;

import com.example.SpringSecurity54.dto.PostDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface PostService {
    List<PostDto> getAllPost(Integer pageNumber,String sortBy,String direction);

    PostDto createNewPost(PostDto postDto);

    PostDto updatePost(PostDto postDto);

    PostDto deletePostById(Long id);

    PostDto partialUpdatePost(@PathVariable Long id, @RequestBody Map<String,Object> map)
}
