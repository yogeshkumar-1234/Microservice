package com.yogesh.service;

import com.yogesh.dto.ReviewMessage;
import com.yogesh.model.Book;
import com.yogesh.repository.BooksRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BooksServiceImpl implements BooksSevice {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksServiceImpl.class);
   @Autowired
   private BooksRepo booksRepo;

    @Override
    public String printBooks()
    {
        return "All_books";
    }
    @Override
    public List<Book> getAllBooks(){
        return booksRepo.findAll();
    }

    @Override
    public Book getBookById(Integer id){
        Optional<Book> optionalBooks  = booksRepo.findById(id);
        LOGGER.info("Getting book by id");
        return optionalBooks.orElse(null);
    }
    @Override
    public Book saveBook(Book book){
        LOGGER.info("Saving new book");
        book.setRating((float) 0);
        return booksRepo.save(book);
    }
    @Override
    public Book updateBook(Book book){
        Optional<Book> existingBook  = booksRepo.findById(book.getId());
        if(existingBook.isPresent()) {
            existingBook.get().setAuthor(book.getAuthor());
            existingBook.get().setName(book.getName());
            existingBook.get().setPublishDate(book.getPublishDate());
            return booksRepo.save(existingBook.get());
        }
        LOGGER.info("Updating Books");
        return null;
    }
    @Override
    public void deleteBookById(Integer id){
        LOGGER.info("Deleting Books");
        booksRepo.deleteById(id);
    }

    @Override
    public Book addReview(ReviewMessage reviewMessage){
        Optional<Book> book = booksRepo.findById(reviewMessage.getBookId());
        if(book.isPresent()) {
            List<String> comments = book.get().getComments();
            if(comments==null)
                comments = new ArrayList<>();
            comments.add(reviewMessage.getComment());
            book.get().setComments(comments);
            book.get().setRating(book.get().getRating());
            return booksRepo.save(book.get());
        }
        LOGGER.info("Added review");
        return null;
    }

}
