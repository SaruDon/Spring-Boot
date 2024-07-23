package com.library.example.Library.Management.service;

import com.library.example.Library.Management.dto.AuthorDto;
import com.library.example.Library.Management.dto.BookDto;
import com.library.example.Library.Management.entity.Author;
import com.library.example.Library.Management.entity.Book;
import com.library.example.Library.Management.repository.AuthorRepo;
import com.library.example.Library.Management.repository.BookRepo;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepo authorRepo;
    private  final ModelMapper modelMapper;
    private final BookRepo bookRepo;

    public AuthorService(AuthorRepo authorRepo, ModelMapper modelMapper, BookRepo bookRepo) {
        this.authorRepo = authorRepo;
        this.modelMapper = modelMapper;
        this.bookRepo = bookRepo;
    }

    public Optional<AuthorDto> getAuthorById(Long authorId) {
       return authorRepo.findById(authorId).map(author -> modelMapper.map(author,AuthorDto.class));
    }

    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        return modelMapper.map(authorRepo.save(author), AuthorDto.class);
    }

    public AuthorDto assignAuthorToBook(Long authorId, Long bookId) {
        Optional<Author> authorE = authorRepo.findById(authorId);
        Optional<Book> bookE = bookRepo.findById(bookId);

        Author assignedAuthor = authorE.flatMap(author ->
                bookE.map(book -> {
                    book.setAuthor(author);
                    bookRepo.save(book);

                    author.getBooks().add(book);
                    return author;
                })).orElse(null);

        return modelMapper.map(assignedAuthor, AuthorDto.class);
    }

    public List<AuthorDto> getAllAuthor() {
        List<Author> authors = authorRepo.findAll();
        return authors.stream()
                .map(author -> modelMapper.map(author,AuthorDto.class))
                .collect(Collectors.toList());
    }

    public Boolean deleteAuthorById(Long authorId) {
        if (authorExist(authorId)) {
            Author author = authorRepo.getReferenceById(authorId);
            bookRepo.deleteAll(author.getBooks()); // delete all books associated with the author
            authorRepo.delete(author); // delete the author
            return true;
        } else {
            return false;
        }
    }

    public Boolean authorExist(Long authorId){
        return  authorRepo.existsById(authorId);
    }
}
