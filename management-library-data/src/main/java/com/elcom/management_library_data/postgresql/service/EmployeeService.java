
package com.elcom.management_library_data.postgresql.service;

import com.elcom.management_library_data.postgresql.dto.EmployeeDto;


public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
}
