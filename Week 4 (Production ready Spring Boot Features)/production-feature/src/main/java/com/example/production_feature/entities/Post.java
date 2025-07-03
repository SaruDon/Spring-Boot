package com.example.production_feature.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;


@Entity(name = "post")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Audited
public class Post extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @NotAudited
    private String description;

//lifecycle hooks for DB
    @PrePersist
    void beforeSave(){

    }

    @PostUpdate
    void beforeUpdate(){

    }

    @PostRemove
    void beforeRemove(){

    }

}
