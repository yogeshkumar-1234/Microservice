package com.yogesh.repository;

import com.yogesh.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepo extends JpaRepository<Book,Integer> {
}
