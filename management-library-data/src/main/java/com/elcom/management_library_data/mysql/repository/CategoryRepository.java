package com.elcom.management_library_data.mysql.repository;

import com.elcom.management_library_data.mysql.model.Author;
import com.elcom.management_library_data.mysql.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CategoryRepository extends JpaRepository<Category, Long>{
    @Query(value = "select * from category where name = ?1", nativeQuery = true)
    Category findCategoryByName(String name);
}
