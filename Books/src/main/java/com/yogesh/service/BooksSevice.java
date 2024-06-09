package com.yogesh.service;

import com.yogesh.dto.ReviewMessage;
import com.yogesh.model.Book;

import java.util.List;


public interface BooksSevice {

    public String printBooks();
    public List<Book> getAllBooks();
    public Book getBookById(Integer id);
    public Book updateBook(Book book);
    public void deleteBookById(Integer id);
    public Book saveBook(Book book);
    public Book addReview(ReviewMessage reviewMessage);
}
