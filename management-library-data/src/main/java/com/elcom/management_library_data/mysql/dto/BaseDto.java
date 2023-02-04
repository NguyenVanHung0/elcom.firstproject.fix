
package com.elcom.management_library_data.mysql.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {
    private Long id;
    
    private String createdBy;
    
    private String modifiedBy;
    
    private Date createdDate;
    
    private Date ModifiedDate;
}
