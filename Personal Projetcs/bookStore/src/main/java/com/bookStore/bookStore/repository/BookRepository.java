package com.bookStore.bookStore.repository;

import com.bookStore.bookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {

}
