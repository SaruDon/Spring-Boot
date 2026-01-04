package com.jpaRevision.demo.controller;

import com.jpaRevision.demo.dto.ProductDto;
import com.jpaRevision.demo.repository.ProductRepository;
import com.jpaRevision.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductRepository productRepository;

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts(@RequestParam(defaultValue = "0") int page){
        return productService.getAllProducts(page);
    }

    @GetMapping("/{id}")
    public Optional<ProductDto> getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }
}
