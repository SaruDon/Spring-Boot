package com.product_ready_features.product_ready_features.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Audited
public class PostEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
   //@NotAudited to skip auditing here
    private String description;
    @PrePersist
    void beforeSave() {
        // This method runs before saving a new entity
        // - Set default values for fields
        // - Perform validation checks
        // - Log creation events
    }
    @PreUpdate
    void updateSave() {
        // This method runs before updating an existing entity
        // - Update timestamps
        // - Perform validation checks before updating
        // - Log update events
    }
    @PostPersist
    void postSave() {
        // This method runs after a new entity has been saved
        // - Perform any post-save operations
        // - Log that the entity has been saved
    }
}
