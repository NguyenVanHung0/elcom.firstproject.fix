
package com.elcom.management_library_data.mysql.repository;

import com.elcom.management_library_data.mysql.model.Author;
import com.elcom.management_library_data.mysql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AuthorRepository extends JpaRepository<Author, Long>{
    @Query(value = "select * from author where name = ?1", nativeQuery = true)
    Author findAuthorByName(String name);
}
