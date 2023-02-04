package com.elcom.management_library_data.mysql.service.imp;

import com.elcom.management_library_data.convert.Mapper;
import com.elcom.management_library_data.mysql.dto.CategoryDto;
import com.elcom.management_library_common.exception.NoSuchElementException;
import com.elcom.management_library_data.mysql.model.Category;
import com.elcom.management_library_data.mysql.repository.CategoryRepository;
import com.elcom.management_library_data.mysql.service.CategoryService;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImp.class);

    @Autowired
    Mapper mapper;
    
    @Autowired
    private RedisTemplate template;

    @Override
    public List<CategoryDto> getListCategory() {
        List<CategoryDto> result = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        if (!categories.isEmpty()) {
            for (Category category : categories) {
                result.add(mapper.toCategoryDto(category));
            }
        }
        return result;
    }

    @Override
    public CategoryDto getCategoryById(Long id) {

           CategoryDto categoryDto = new CategoryDto();
           Gson g = new Gson();
           if(template.opsForValue().get("category" + id) == null){
              Category category =  categoryRepository.findById(id).orElse(null);
              if(category != null){
                  categoryDto = mapper.toCategoryDto(category);
                  String categorySave = g.toJson(categoryDto);
                  template.opsForValue().set("category" + id, categorySave);
                  System.out.println("lay trong csdl");
                  return categoryDto;
              }
              
              throw new NoSuchElementException("The loai khong ton tai");
           }
           else{
               String categoryGet = template.opsForValue().get("category" + id).toString();
               categoryDto = g.fromJson(categoryGet, CategoryDto.class);
               System.out.println("lay trong redis");
               return categoryDto;
           }

    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        if(categoryDto != null){
            category = mapper.toCategory(categoryDto);
            category.setCreatedDate(new Date());
            categoryRepository.save(category);
        }
        return mapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
        Category oldCategory = categoryRepository.findById(id).orElse(null);
        Category category = new Category();
        if(oldCategory != null){
            category = mapper.toCategory(oldCategory, categoryDto);
            category.setId(id);
            category.setModifiedDate(new Date());
            categoryRepository.save(category);
            return mapper.toCategoryDto(category);
        }
         throw new NoSuchElementException("The loai khong ton tai");
    }

    @Override
    public String deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category != null){
            categoryRepository.deleteById(id);
            return "Xoa thanh cong";
        }
        throw new NoSuchElementException("The loai khong ton tai");
    }
    
    @Override
    public List<String> crawlCategory() {
        String url = "https://nhasachmienphi.com/";
        List<String> listCategoryText = new ArrayList();
        try {
            Document dc = Jsoup.connect(url).get();
            Element container = dc.getElementsByClass("row").get(1);
            Elements aTags = container.getElementsByTag("a");
            for (Element atag : aTags) {
                String aText = atag.ownText();
                if (!aText.equals("")) {
                    listCategoryText.add(aText);
                }
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(CategoryServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (String aText : listCategoryText) {
            Category category = new Category();
            category.setName(aText);
            category.setCreatedDate(new Date());
            categoryRepository.save(category);
        }
        return listCategoryText;

    }

}
