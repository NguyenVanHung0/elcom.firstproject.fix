
package com.elcom.management_library_data.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto extends  BaseDto{

    private String name;
    
    private String nhaXB;
    
    private String namXB;
    
    private String firstText;
    
    private Long authorId;
    
    private Long categoryId;
}
