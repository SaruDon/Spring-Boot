package com.example.production_feature.service.impl;

import com.example.production_feature.dto.PostDto;
import com.example.production_feature.entities.Post;
import com.example.production_feature.exception.ResourceNotFoundException;
import com.example.production_feature.repository.PostRepository;
import com.example.production_feature.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPost() {
        return postRepository
                .findAll()
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto postDto) {
        return  modelMapper.map(postRepository.save(modelMapper.map(postDto,Post.class)), PostDto.class);
    }

    @Override
    public PostDto getPostById(Long id) {
        Optional<PostDto> postDto = postRepository.findById(id).map(post -> modelMapper.map(post, PostDto.class));
        return postDto.orElseThrow(()-> new ResourceNotFoundException("Post By id"+id+" Does not exists"));
    }

    @Override
    public PostDto partialUpdatePost(Map<String, Object> updates, Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post with Id"+id+"not found"));

        BeanWrapper beanWrapper = new BeanWrapperImpl(post);
        updates.forEach((property,value)->{
            if (beanWrapper.isWritableProperty(property)){
                beanWrapper.setPropertyValue(property,value);
            }else{
                throw new IllegalArgumentException("Property '" + property + "' not found on Post");
            }
        });
        return modelMapper.map(postRepository.save(post),PostDto.class);
    }
}
