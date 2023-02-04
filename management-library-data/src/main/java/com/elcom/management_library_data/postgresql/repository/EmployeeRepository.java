
package com.elcom.management_library_data.postgresql.repository;

import com.elcom.management_library_data.postgresql.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
}
