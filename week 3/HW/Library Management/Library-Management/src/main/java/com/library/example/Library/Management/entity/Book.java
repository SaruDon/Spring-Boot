package com.library.example.Library.Management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"books", "publications"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    private String title;

    @CreationTimestamp
    private LocalDateTime bookReleaseDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "publication_id")
    private Publication publication;
}
