
package com.elcom.management_library_data.mysql.service;

import com.elcom.management_library_data.mysql.dto.CategoryDto;
import java.util.List;

public interface CategoryService{
    List<CategoryDto> getListCategory();
    CategoryDto getCategoryById(Long id);
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Long id);
    String deleteCategory(Long id);
    List<String> crawlCategory();
}
