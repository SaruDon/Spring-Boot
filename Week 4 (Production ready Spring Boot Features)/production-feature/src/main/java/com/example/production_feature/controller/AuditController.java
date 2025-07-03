package com.example.production_feature.controller;

import com.example.production_feature.entities.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/audit")
@RequiredArgsConstructor
public class AuditController {


    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @GetMapping(path = "/post/{postId}")
    public ResponseEntity<List<Post>> getAllAuditByPostId(@PathVariable Long postId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            AuditReader reader = AuditReaderFactory.get(entityManager);
            List<Number> revisions = reader.getRevisions(Post.class, postId);

            List<Post> postRevisions = revisions.stream()
                    .map(rev -> reader.find(Post.class, postId, rev))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(postRevisions);
        } finally {
            entityManager.close();
        }
    }

}
