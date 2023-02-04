
package com.elcom.management_library_api.controller;

import com.elcom.management_library_data.mysql.service.DetailBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("detailbook")
public class DetailBookController {
    
    @Autowired
    DetailBookService detailBookService;
    
    @GetMapping("")
    public ResponseEntity<?> crawlDetailBook(){
        return ResponseEntity.ok(detailBookService.crawlDetailBook());
    }
    
    
}
