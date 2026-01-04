package com.jpaRevision.demo.service;

import com.jpaRevision.demo.config.ModelMapperConfig;
import com.jpaRevision.demo.dto.ProductDto;
import com.jpaRevision.demo.entity.ProductEntity;
import com.jpaRevision.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    private static final int PAGE_SIZE = 5;


    public List<ProductDto> getAllProducts(Integer pageNumber){

        Pageable pageable = PageRequest.of(pageNumber,PAGE_SIZE);


        List<ProductEntity> productEntityList = productRepository.findAll(pageable).getContent();
        return productEntityList
                .stream()
                .map(productEntity -> modelMapper.map(productEntity,ProductDto.class))
                .collect((Collectors.toList()));
    }

    public Optional<ProductDto> getProductById(Long id){
        return  productRepository.findById(id).map(productEntity -> modelMapper.map(productEntity,ProductDto.class));
    }
}