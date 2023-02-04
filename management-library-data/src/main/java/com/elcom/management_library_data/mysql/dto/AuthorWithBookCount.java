package com.elcom.management_library_data.mysql.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorWithBookCount {
    private Long authorId;
    private int total;
    
    public AuthorWithBookCount(Long id, int total){
        this.authorId = id;
        this.total = total;
    }
}
