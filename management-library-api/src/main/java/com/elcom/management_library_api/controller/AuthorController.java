
package com.elcom.management_library_api.controller;

import com.elcom.management_library_data.mysql.service.AuthorService;
import com.elcom.management_library_data.mysql.dto.AuthorDto;
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
@RequestMapping("author")
public class AuthorController {
    
    @Autowired
    AuthorService authorService;
    
   
    @GetMapping("")
    public ResponseEntity<?> getListAuthor(){
        return ResponseEntity.ok(authorService.getListAuthor());
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id){
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }
    
    @PostMapping("")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorDto authorDto){
        return ResponseEntity.ok(authorService.createAuthor(authorDto));
    }
    
    @PutMapping("{id}")
    public ResponseEntity<?> updateAuthor( @PathVariable Long id, @RequestBody AuthorDto authorDto){
        return ResponseEntity.ok(authorService.updateAuthor(authorDto, id));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id){
        return ResponseEntity.ok(authorService.deleteAuthor(id));
    }
}
