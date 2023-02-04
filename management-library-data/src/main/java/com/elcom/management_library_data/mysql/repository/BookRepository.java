package com.elcom.management_library_data.mysql.repository;


import com.elcom.management_library_data.mysql.model.Author;
import com.elcom.management_library_data.mysql.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("bookmysql")
public interface BookRepository extends JpaRepository<Book, Long>{
    @Query(value = "select * from book where name = ?1", nativeQuery = true)
    Book findBookByName(String name);
    
}
