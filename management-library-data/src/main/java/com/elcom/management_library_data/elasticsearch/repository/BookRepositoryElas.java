
package com.elcom.management_library_data.elasticsearch.repository;

import com.elcom.management_library_data.elasticsearch.model.BookElas;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryElas extends ElasticsearchRepository<BookElas, Long>{
     List<BookElas> findByName(String name);
}
