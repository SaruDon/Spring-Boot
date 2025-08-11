package com.sarvesh.SecurityApp.SecurityApplication.service;

import com.sarvesh.SecurityApp.SecurityApplication.dto.PostDto;
import com.sarvesh.SecurityApp.SecurityApplication.entity.Post;
import com.sarvesh.SecurityApp.SecurityApplication.entity.User;
import com.sarvesh.SecurityApp.SecurityApplication.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public List<PostDto> getAllPosts(){
        return postRepository.findAll()
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    public PostDto getPostById(Long postId){
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("user {}",user);
        return modelMapper.map(postRepository.findById(postId), PostDto.class);
    }

    public PostDto createNewPost(PostDto postDto){
        Post post = modelMapper.map(postDto,Post.class);
            return modelMapper.map(postRepository.save(post),PostDto.class);
    }

}
