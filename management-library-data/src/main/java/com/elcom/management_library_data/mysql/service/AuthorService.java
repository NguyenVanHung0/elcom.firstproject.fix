package com.elcom.management_library_data.mysql.service;

import com.elcom.management_library_data.mysql.dto.AuthorDto;
import java.util.List;


public interface AuthorService {
    List<AuthorDto> getListAuthor();
    AuthorDto getAuthorById(Long id);
    AuthorDto createAuthor(AuthorDto authorDto);
    AuthorDto updateAuthor(AuthorDto authorDto, Long id);
    String deleteAuthor(Long id);
    List<String> crawlAuthor();
}
