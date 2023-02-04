package com.elcom.management_library_api.controller;

import com.elcom.management_library_data.elasticsearch.service.BookServiceElas;
import com.elcom.management_library_data.mysql.dto.BookDto;
import com.elcom.management_library_data.mysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;
    
    @Autowired
    BookServiceElas bookServiceElas;

    @GetMapping("")
    public ResponseEntity<?> getListBook() {
        return ResponseEntity.ok(bookService.getListBook());
    }
    
    
    @GetMapping("{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createBook(@RequestBody BookDto bookDto) {
        bookService.createBook(bookDto);
        return ResponseEntity.ok(bookServiceElas.createBook(bookDto));
        //return ResponseEntity.ok(bookService.createBook(bookDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        bookServiceElas.updateBook(id, bookDto);
        return ResponseEntity.ok(bookService.updateBook(id, bookDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookServiceElas.deleteBook(id);
        return ResponseEntity.ok(bookService.deleteBook(id));
    }
    
    @DeleteMapping("")
    public ResponseEntity<?> deleteAllBook() {
        bookServiceElas.deleteAllBook();
        return ResponseEntity.ok(bookService.deleteAllBook());
    }

    @GetMapping("count/author")
    public ResponseEntity<?> getCountBookByAuthor() {
        return ResponseEntity.ok(bookService.AuthorWithBookCount());
    }

    @GetMapping("count/category")
    public ResponseEntity<?> getCountBookByCategory() {
        return ResponseEntity.ok(bookService.CategoryWithBookCount());
    }

    @GetMapping("count/firsttext")
    public ResponseEntity<?> getCountBookByFirstText() {
        return ResponseEntity.ok(bookService.FirstTextWithBookCount());
    }
    
    // Elasticsearch
    @GetMapping("elas")
    public ResponseEntity<?> getListBookAlas() {
        return ResponseEntity.ok(bookServiceElas.getListBook());
    }
    
    @GetMapping("elas/{name}")
    public ResponseEntity<?> getBookElasByName(@PathVariable String name) {
        return ResponseEntity.ok(bookServiceElas.getBookByName(name));
    }
    
    @GetMapping("elasid/{id}")
    public ResponseEntity<?> getBookElasById(@PathVariable Long id) {
        return ResponseEntity.ok(bookServiceElas.getBookById(id));
    }
}
