package com.example.SpringSecurity57.service.impl;

import com.example.SpringSecurity57.dto.PostDto;
import com.example.SpringSecurity57.entity.Post;
import com.example.SpringSecurity57.exception.ResourceNotFoundException;
import com.example.SpringSecurity57.repostory.PostRepository;
import com.example.SpringSecurity57.service.PostService;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private  final PostRepository postRepository;
    private final ModelMapper modelMapper;

    private static final int PAGE_SIZE = 5;

    @Override
    public List<PostDto> getAllPost(Integer pageNumber, String sortBy, String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("ASC") ?
                Sort.Direction.ASC :
                Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(pageNumber,PAGE_SIZE,Sort.by(sortDirection,sortBy));

        Page<Post> pages = postRepository.findAll(pageable);

        List<Post> postList = pages.getContent();

        return postList.stream().map((element) -> modelMapper.map(element, PostDto.class)).collect(Collectors.toList());
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
    public PostDto deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));

        postRepository.deleteById(id);

        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post =postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found with id"+id));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto partialUpdatePost(Long id, Map<String, Object> map) {
        Post postToBeUpdated = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found"));

        map.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Post.class,key);

            if (field==null){
                throw new RuntimeException("Invalid field: " + key);
            }

            field.setAccessible(true);

            ReflectionUtils.setField(field,postToBeUpdated,value);
        });

        Post savedPost = postRepository.save(postToBeUpdated);

        return modelMapper.map(savedPost, PostDto.class);
    }


}
