
package com.elcom.management_library_api.controller;

import com.elcom.management_library_data.postgresql.dto.EmployeeDto;
import com.elcom.management_library_data.postgresql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;
    
    @PostMapping("")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.createEmployee(employeeDto));
    }
}
