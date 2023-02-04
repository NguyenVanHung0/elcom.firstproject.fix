
package com.elcom.management_library_data.elasticsearch.service.imp;

import com.elcom.management_library_data.convert.Mapper;
import com.elcom.management_library_data.elasticsearch.model.BookElas;
import com.elcom.management_library_data.elasticsearch.repository.BookRepositoryElas;
import com.elcom.management_library_data.elasticsearch.service.BookServiceElas;
import com.elcom.management_library_common.exception.NoSuchElementException;
import com.elcom.management_library_data.mysql.dto.BookDto;
import com.elcom.management_library_data.mysql.model.Book;
import com.elcom.management_library_data.mysql.repository.AuthorRepository;
import com.elcom.management_library_data.mysql.repository.BookRepository;
import com.elcom.management_library_data.mysql.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceElasImp implements BookServiceElas{

    @Autowired
    BookRepositoryElas bookRepositoryElas;
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    AuthorRepository authorRepository;
    
    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    Mapper mapper;
    
    @Override
    public BookDto createBook(BookDto bookDto) {
        BookElas book = new BookElas();
        if(bookDto != null){
            book = mapper.toBookElas(bookDto);
            book.setCreatedDate(new Date());
            List<Book> listBook = bookRepository.findAll();
            Long idLastest = listBook.get(listBook.size()-1).getId();
            book.setId(idLastest.toString());
            bookRepositoryElas.save(book);
        }
        return mapper.toBookDto(book);
        
    }

    @Override
    public List<BookDto> getListBook() {
        List<BookElas> books = new ArrayList<>();
        List<BookDto> bookDto = new ArrayList<>();
        Iterable<BookElas> result = bookRepositoryElas.findAll();
        for(BookElas bookElas : result){
            books.add(bookElas);
        }
        for(BookElas book : books){
            bookDto.add(mapper.toBookDto(book));
        }
        return bookDto;
    }

    @Override
    public List<BookDto> getBookByName(String name) {
        List<BookElas> books = bookRepositoryElas.findByName(name);
        List<BookDto> bookDto = new ArrayList<>();
        for(BookElas book : books){
            bookDto.add(mapper.toBookDto(book));
        }
        return bookDto;
    }

    @Override
    public BookDto getBookById(Long id) {
        BookElas book = bookRepositoryElas.findById(id).orElse(null);
        BookDto bookDto = new BookDto();
        if (book != null) {
            bookDto = mapper.toBookDto(book);
            return bookDto;
        }
        // Ném ra NoSuchElementException
        throw new NoSuchElementException("Sach khong ton tai");
    }

    @Override
    public BookDto updateBook(Long id, BookDto bookDto) {
        BookElas oldBookElas = bookRepositoryElas.findById(id).orElse(null);
        BookElas newBookElas = new BookElas();
        if (oldBookElas != null) {
            newBookElas = mapper.toBookElas(oldBookElas, bookDto);
            newBookElas.setModifiedDate(new Date());
            bookRepositoryElas.save(newBookElas);
            return mapper.toBookDto(newBookElas);
        }
        // Ném ra NoSuchElementException
        throw new NoSuchElementException("Sach khong ton tai");
    }

    @Override
    public BookDto deleteBook(Long id) {
        BookElas book = bookRepositoryElas.findById(id).orElse(null);
        if (book != null) {
            bookRepositoryElas.deleteById(id);
            return mapper.toBookDto(book);
        }
        // Ném ra NoSuchElementException
        throw new NoSuchElementException("Sach khong ton tai");
    }

    @Override
    public String deleteAllBook() {
        try {
            bookRepositoryElas.deleteAll();
        } catch (Exception e) {
            return "co loi: " + e.getMessage();
        }
        return "xoa thanh cong";
    }
}
