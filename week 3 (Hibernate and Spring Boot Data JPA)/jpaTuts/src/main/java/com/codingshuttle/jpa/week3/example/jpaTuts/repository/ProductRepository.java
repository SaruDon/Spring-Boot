package com.codingshuttle.jpa.week3.example.jpaTuts.repository;

import com.codingshuttle.jpa.week3.example.jpaTuts.entities.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findByTitle(String title, Pageable pageable);

    List<ProductEntity> findByCreatedAtAfterOrderByTitle(LocalDateTime after);

    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(int quantity, BigDecimal price);

    List<ProductEntity> findByTitleLike(String title);

    List<ProductEntity> findByTitleContainingIgnoreCase(String title,  Pageable pageable);

    Optional<ProductEntity> findByTitleAndPrice(String peps, BigDecimal bigDecimal);
}
