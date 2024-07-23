package com.product_ready_features.product_ready_features.service.impl;

import com.product_ready_features.product_ready_features.dto.PostDto;
import com.product_ready_features.product_ready_features.entity.PostEntity;
import com.product_ready_features.product_ready_features.exception.ResourceNotFoundException;
import com.product_ready_features.product_ready_features.repository.PostRepository;
import com.product_ready_features.product_ready_features.service.PostService;
import jakarta.persistence.Embeddable;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    final private PostRepository postRepository;
    final private ModelMapper modelMapper;



    @Override
    public List<PostDto> getAllPost() {
        return postRepository.findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity,PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        PostEntity postEntity = modelMapper.map(postDto, PostEntity.class);
        PostEntity savedPost = postRepository.save(postEntity);
        return modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post Id not found" + postId));
        return modelMapper.map(postEntity,PostDto.class);
    }

    @Override
    public PostDto updatePostById(PostDto inputPost, Long postId) {
        PostEntity olderPost = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Id not fount" + postId));
        inputPost.setId(postId);
        //modelMapper.map(inputPost, olderPost): This line maps the properties of inputPost (which is a PostDto)
        // to olderPost (which is a PostEntity). This effectively updates olderPost with
        // the values from inputPost, without changing the olderPost's ID.
        modelMapper.map(inputPost,olderPost);
        PostEntity savedPostEntity = postRepository.save(olderPost);
        return modelMapper.map(savedPostEntity, PostDto.class);
    }
}
