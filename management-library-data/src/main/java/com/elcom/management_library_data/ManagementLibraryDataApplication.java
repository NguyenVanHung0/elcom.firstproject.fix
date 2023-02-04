
package com.elcom.management_library_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
"com.elcom.management_library_api", 
"com.elcom.management_library_common", 
"com.elcom.management_library_data"})
public class ManagementLibraryDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagementLibraryDataApplication.class, args);
    }
}
