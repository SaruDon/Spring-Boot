package com.product_ready_features.product_ready_features.service;


import com.product_ready_features.product_ready_features.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    List<PostDto> getAllPost();

    PostDto createPost(PostDto postDto);

    PostDto getPostById(Long postId);

    PostDto updatePostById(PostDto postDto, Long postId);
}
