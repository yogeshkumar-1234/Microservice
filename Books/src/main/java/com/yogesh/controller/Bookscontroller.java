package com.yogesh.controller;

import com.yogesh.model.Book;
import com.yogesh.service.BooksSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books/v1")
@Validated
public class Bookscontroller {

    @Autowired
    private BooksSevice booksSevice;


    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok().body(booksSevice.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id){
        return ResponseEntity.ok().body(booksSevice.getBookById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return ResponseEntity.ok().body(booksSevice.saveBook(book));
    }

    @PutMapping("/")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        return ResponseEntity.ok().body(booksSevice.updateBook(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id){
        booksSevice.deleteBookById(id);
        return ResponseEntity.ok().body("Deleted book successfully");
    }
}
