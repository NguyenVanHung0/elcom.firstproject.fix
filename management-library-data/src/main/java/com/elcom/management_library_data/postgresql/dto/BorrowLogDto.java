
package com.elcom.management_library_data.postgresql.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BorrowLogDto {
    private Long id;
    
    private String userName;
    
    private String authorName;
    
    private String bookName;
    
    private String categoryBook;
    
    private Date borrowDate;
}
