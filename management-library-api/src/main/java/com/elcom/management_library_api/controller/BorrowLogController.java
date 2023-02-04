
package com.elcom.management_library_api.controller;

import com.elcom.management_library_data.postgresql.dto.BorrowLogDto;
import com.elcom.management_library_data.postgresql.dto.BorrowLogNameWithTime;
import com.elcom.management_library_data.postgresql.dto.BorrowLogWithTime;
import com.elcom.management_library_data.postgresql.service.BorrowLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("borrowlog")
public class BorrowLogController {
    @Autowired
    BorrowLogService borrowLogService;
    
//    @GetMapping("")
//     ResponseEntity<?> createBorrowLog(){
//        return ResponseEntity.ok(borrowLogService.getAllBorrowLog());
//    }
    
    @PostMapping("")
    ResponseEntity<?> createBorrowLog(@RequestBody BorrowLogDto borrowLogDto){
        return ResponseEntity.ok(borrowLogService.createBorrowLog(borrowLogDto));
    }
    
    @PutMapping("{id}")
    ResponseEntity<?> updateBorrowLog(@PathVariable Long id, @RequestBody BorrowLogDto borrowLogDto){
        return ResponseEntity.ok(borrowLogService.updateBorrowLog(id, borrowLogDto));
    }
    
    @GetMapping("countborrowlog")
    ResponseEntity<?> countBorrowLogWithTime(@RequestBody BorrowLogWithTime borrowLogWithTime){
        return ResponseEntity.ok(borrowLogService.countBorrowLogWithTime(borrowLogWithTime));
    }
    
    @GetMapping("maxborrowlog")
    ResponseEntity<?> maxBorrowLogNameWithTime(@RequestBody BorrowLogNameWithTime borrowLogNameWithTime){
        return ResponseEntity.ok(borrowLogService.maxBorrowLogBook(borrowLogNameWithTime));
    }
    
    
}
