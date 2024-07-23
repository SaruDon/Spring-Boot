package com.product_ready_features.product_ready_features.repository;

import com.product_ready_features.product_ready_features.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
