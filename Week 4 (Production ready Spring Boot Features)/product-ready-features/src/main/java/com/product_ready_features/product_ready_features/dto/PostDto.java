package com.product_ready_features.product_ready_features.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {

    private Long id;

    private String title;

    private String description;
}
