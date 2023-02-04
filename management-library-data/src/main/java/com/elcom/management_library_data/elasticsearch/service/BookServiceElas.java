
package com.elcom.management_library_data.elasticsearch.service;

import com.elcom.management_library_data.mysql.dto.BookDto;
import java.util.List;

public interface BookServiceElas {
    BookDto createBook(BookDto bookDto);
    List<BookDto> getListBook();
    List<BookDto> getBookByName(String name);
    BookDto updateBook(Long id,BookDto bookDto );
    BookDto getBookById(Long id);
    BookDto deleteBook(Long id);
    String deleteAllBook();
}
