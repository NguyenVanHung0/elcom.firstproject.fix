
package com.elcom.management_library_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
"com.elcom.management_library_api", 
"com.elcom.management_library_common", 
"com.elcom.management_library_data"})
public class ManagementLibraryApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagementLibraryApiApplication.class, args);
    }
}
