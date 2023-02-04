
package com.elcom.management_library_data.mysql.repository;

import com.elcom.management_library_data.mysql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value = "select * from user where username = ?1", nativeQuery = true)
    User findByUsername(String username);
}
