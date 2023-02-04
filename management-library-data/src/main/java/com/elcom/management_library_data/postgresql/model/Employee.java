
package com.elcom.management_library_data.postgresql.model;

import com.elcom.management_library_data.mysql.model.Base;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Base{
    
    @Column
    private String name;
    
    @Column(name = "birth_day")
    private Date birthDay;
    
    @Column
    private String phone;
    
    @Column
    private String address;
}
