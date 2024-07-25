package com.library.example.Library.Management.controller;

import com.library.example.Library.Management.dto.BookDto;
import com.library.example.Library.Management.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "book")
public class Book {


    final private BookService bookService;

    public Book(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long bookId) {
        Optional<BookDto> bookDto = bookService.getBookById(bookId);
        return bookDto
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @DeleteMapping(path = "{bookId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long bookId){
        boolean isDeleted = bookService.deleteBookById(bookId);
        if (isDeleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto  bookDto){
        return ResponseEntity.ok(bookService.createBook(bookDto));
    }
}
