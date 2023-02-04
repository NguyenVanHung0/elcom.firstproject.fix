
package com.elcom.management_library_data.mysql.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryWithBookCount {
    private Long categoryId;
    private int total;
    
    public CategoryWithBookCount(Long id, int total){
        this.categoryId = id;
        this.total = total;
    }
}
