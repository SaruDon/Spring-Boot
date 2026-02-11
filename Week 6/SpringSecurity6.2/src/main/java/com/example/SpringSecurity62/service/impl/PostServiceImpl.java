package com.example.SpringSecurity62.service.impl;

import com.example.SpringSecurity62.dto.PostDto;
import com.example.SpringSecurity62.entity.Post;
import com.example.SpringSecurity62.exceptions.ResourceNotFoundException;
import com.example.SpringSecurity62.repository.PostRepository;
import com.example.SpringSecurity62.service.PostService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;
    private final Integer PAGE_SIZE=10;
    private final ModelMapper modelMapper;


    @Override
    public List<PostDto> getAllPost(Integer pageNumber, String sortBy, String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("ASE")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(pageNumber,PAGE_SIZE, Sort.by(sortDirection,sortBy));


        Page<Post> pages = postRepository.findAll(pageable);
        return pages.map((element) -> modelMapper.map(element, PostDto.class)).stream().toList();
    }


    @Override
    public PostDto createNewPost(PostDto postDto) {
        return modelMapper.map(postRepository.save((modelMapper.map(postDto,Post.class))),PostDto.class);
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {

        Post updateThisPost = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));
        Post post = modelMapper.map(postDto,Post.class);

        post.setId(updateThisPost.getId());
        return modelMapper.map(postRepository.save(post),PostDto.class);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));

        postRepository.deleteById(id);

    }

    @Override
    public PostDto getPostById(Long id) {
        Post post =postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found with id"+id));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto partialUpdate(Long id, Map<String, Objects> updates) {

        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(
                "Post with id "+id +"Not found"
        ));

        updates.forEach((key,value)->{

            Field field = ReflectionUtils.findField(Post.class,key);

            if(field==null){
                throw  new RuntimeException("Fields not found");
            }

            field.setAccessible(true);

            ReflectionUtils.setField(field,post,value);
        });


        return modelMapper.map(post, PostDto.class);

    }

}
