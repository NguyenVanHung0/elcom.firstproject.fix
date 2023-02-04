package com.elcom.management_library_data.postgresql.service.imp;

import com.elcom.management_library_data.convert.Mapper;
import com.elcom.management_library_common.exception.NoSuchElementException;
import com.elcom.management_library_data.mysql.dto.Response;
import com.elcom.management_library_data.mysql.model.Author;
import com.elcom.management_library_data.mysql.model.Book;
import com.elcom.management_library_data.mysql.model.Category;
import com.elcom.management_library_data.mysql.model.User;
import com.elcom.management_library_data.mysql.repository.AuthorRepository;
import com.elcom.management_library_data.mysql.repository.BookRepository;
import com.elcom.management_library_data.mysql.repository.CategoryRepository;
import com.elcom.management_library_data.mysql.repository.UserRepository;
import com.elcom.management_library_data.postgresql.dto.BorrowLogDto;
import com.elcom.management_library_data.postgresql.dto.BorrowLogNameWithTime;
import com.elcom.management_library_data.postgresql.dto.BorrowLogWithTime;
import com.elcom.management_library_data.postgresql.model.BorrowLog;
import com.elcom.management_library_data.postgresql.repository.BorrowLogRepository;
import com.elcom.management_library_data.postgresql.service.BorrowLogService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BorrowLogServiceImp implements BorrowLogService {

    @Autowired
    BorrowLogRepository borrowLogRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Mapper mapper;

    @Override
    public Response createBorrowLog(BorrowLogDto borrowLogDto) {
        BorrowLog borrowLog = new BorrowLog();
        if (borrowLogDto != null) {
            Author author = authorRepository.findAuthorByName(borrowLogDto.getAuthorName());
            Category category = categoryRepository.findCategoryByName(borrowLogDto.getCategoryBook());
            User user = userRepository.findByUsername(borrowLogDto.getUserName());
            Book book = bookRepository.findBookByName(borrowLogDto.getBookName());
            if (author == null) {
                return new Response("Author khong ton tai", HttpStatus.BAD_REQUEST);
            }
            if (category == null) {
                return new Response("Category khong ton tai", HttpStatus.BAD_REQUEST);
            }
            if (user == null) {
                return new Response("User khong ton tai", HttpStatus.BAD_REQUEST);
            }
            if (book == null) {
                return new Response("Book khong ton tai", HttpStatus.BAD_REQUEST);
            }
            borrowLog = mapper.toBorrowLog(borrowLogDto);
            borrowLogRepository.save(borrowLog);
            return new Response("Tao moi thanh cong", HttpStatus.CREATED);
        }
        return new Response("Truyen du lieu de tao", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Response updateBorrowLog(Long id, BorrowLogDto borrowLogDto) {
        BorrowLog borrowLog = new BorrowLog();
        if (borrowLogDto != null) {
            BorrowLog borrowLogOld = borrowLogRepository.findById(id).orElse(null);
            if (borrowLogOld != null) {
                Author author = authorRepository.findAuthorByName(borrowLogDto.getAuthorName());
                Category category = categoryRepository.findCategoryByName(borrowLogDto.getCategoryBook());
                User user = userRepository.findByUsername(borrowLogDto.getUserName());
                Book book = bookRepository.findBookByName(borrowLogDto.getBookName());
                if (author == null) {
                    return new Response("Author khong ton tai", HttpStatus.BAD_REQUEST);
                }
                if (category == null) {
                    return new Response("Category khong ton tai", HttpStatus.BAD_REQUEST);
                }
                if (user == null) {
                    return new Response("User khong ton tai", HttpStatus.BAD_REQUEST);
                }
                if (book == null) {
                    return new Response("Book khong ton tai", HttpStatus.BAD_REQUEST);
                }
                borrowLog = mapper.toBorrowLog(borrowLogOld, borrowLogDto);
                borrowLogRepository.save(borrowLog);
                return new Response("Cap nhat thanh cong", HttpStatus.CREATED);
            }
            throw new NoSuchElementException("ban ghi muon sach khong ton tai");

        }
        return new Response("Truyen du lieu de tao moi", HttpStatus.BAD_REQUEST);
    }

    @Override
    public BorrowLogWithTime countBorrowLogWithTime(BorrowLogWithTime borrowLogWithTime) {
        try {
            borrowLogWithTime.setCount(borrowLogRepository.countNumberOfBorrowLog(new SimpleDateFormat("yyyy-MM-dd").parse(borrowLogWithTime.getDateStart()), new SimpleDateFormat("yyyy-MM-dd").parse(borrowLogWithTime.getDateEnd())));
            return borrowLogWithTime;
        } catch (ParseException ex) {
            Logger.getLogger(BorrowLogServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<BorrowLogNameWithTime> maxBorrowLogBook(BorrowLogNameWithTime borrowLogNameWithTime) {
        try {
            List<BorrowLogNameWithTime> listBorrowLogNameWithTime = new ArrayList<>();
            listBorrowLogNameWithTime = borrowLogRepository.listBorrowLogNameWithTime(new SimpleDateFormat("yyyy-MM-dd").parse(borrowLogNameWithTime.getDateStart()), new SimpleDateFormat("yyyy-MM-dd").parse(borrowLogNameWithTime.getDateEnd()));
            for (BorrowLogNameWithTime borrowLogNameWithTime1 : listBorrowLogNameWithTime) {
                borrowLogNameWithTime1.setDateStart(borrowLogNameWithTime.getDateStart());
                borrowLogNameWithTime1.setDateEnd(borrowLogNameWithTime.getDateEnd());
            }
            return listBorrowLogNameWithTime;
        } catch (ParseException ex) {
            Logger.getLogger(BorrowLogServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<BorrowLogDto> getAllBorrowLog() {
        List<BorrowLog> listBorrowLog = borrowLogRepository.findAll();
        List<BorrowLogDto> listResult = new ArrayList<>();
        for (BorrowLog borrowLog : listBorrowLog) {
            listResult.add(mapper.toBorrowLogDto(borrowLog));
        }
        return listResult;
    }
}
