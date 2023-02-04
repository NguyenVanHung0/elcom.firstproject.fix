
package com.elcom.management_library_api.controller;

import com.elcom.management_library_data.mysql.service.AuthorService;
import com.elcom.management_library_data.mysql.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("crawl")
public class CrawlController {
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    AuthorService authorService;
    
    @GetMapping("category")
    public ResponseEntity<?> crawlCategory(){
        return ResponseEntity.ok(categoryService.crawlCategory());
    } 
    
    @GetMapping("author")
    public ResponseEntity<?> crawlAuthor(){
        return ResponseEntity.ok(authorService.crawlAuthor());
    }
}
