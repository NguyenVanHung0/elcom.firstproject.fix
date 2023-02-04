
package com.elcom.management_library_data.mysql.repository;

import com.elcom.management_library_data.mysql.model.DetailBook;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DetailBookRepository extends JpaRepository<DetailBook, Long>{
    
}
