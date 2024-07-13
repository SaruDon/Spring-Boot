package com.bookStore.bookStore.service.impl;

import com.bookStore.bookStore.entity.Book;
import com.bookStore.bookStore.repository.BookRepository;
import com.bookStore.bookStore.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }
}
