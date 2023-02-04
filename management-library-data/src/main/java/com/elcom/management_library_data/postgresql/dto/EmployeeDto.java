
package com.elcom.management_library_data.postgresql.dto;

import com.elcom.management_library_data.mysql.dto.BaseDto;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto extends BaseDto{
    private String name;
    
    private Date birthDay;
    
    private String phone;
    
    private String address;
}
