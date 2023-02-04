
package com.elcom.management_library_data.postgresql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowLogWithTime {
    private String dateStart;
    
    private String dateEnd;
    
    private Long count;
}
