package com.library.example.Library.Management.dto;

import com.library.example.Library.Management.entity.Author;
import com.library.example.Library.Management.entity.Book;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDto {
    private  Long id;

    private String name;

    private List<BookDto> books;

    private List<AuthorDto> authors;
}
