package com.library.example.Library.Management.service;


import com.library.example.Library.Management.dto.BookDto;
import com.library.example.Library.Management.entity.Book;
import com.library.example.Library.Management.repository.AuthorRepo;
import com.library.example.Library.Management.repository.BookRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    final private BookRepo bookRepo;
    final private AuthorRepo authorRepo;
    final private ModelMapper modelMapper;

    public BookService(BookRepo bookRepo, AuthorRepo authorRepo, ModelMapper modelMapper) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.modelMapper = modelMapper;
    }

    public Optional<BookDto> getBookById(Long bookId) {
        return bookRepo.findById(bookId).map(book -> modelMapper.map(book,BookDto.class));
    }


    public BookDto createBook(BookDto bookDto) {
        Book bookToSave = modelMapper.map(bookDto,Book.class);
        return modelMapper.map(bookRepo.save(bookToSave),BookDto.class);
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepo.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }



    public Boolean deleteBookById(Long bookId) {
        if (!bookExist(bookId)) return false;
        bookRepo.deleteById(bookId);
        return true;
    }

    public Boolean bookExist(Long bookId){
        return  bookRepo.existsById(bookId);
    }
}
