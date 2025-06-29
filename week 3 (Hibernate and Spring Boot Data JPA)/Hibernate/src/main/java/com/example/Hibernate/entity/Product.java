package com.example.Hibernate.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.annotation.processing.Generated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "product_table",
        uniqueConstraints = {
                @UniqueConstraint(name = "sku_unique", columnNames = {"sku"}),
                @UniqueConstraint(name = "title_price_unique",columnNames = {"title","price"})//title and price can not be same
        },
        indexes={
          @Index(name = "sku_index", columnList = "sku")
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sku;

    @Column(name = "title_x")
    private String title;

    private BigDecimal price;

    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
