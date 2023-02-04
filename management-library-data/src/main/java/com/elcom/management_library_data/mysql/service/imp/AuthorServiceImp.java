package com.elcom.management_library_data.mysql.service.imp;

import com.elcom.management_library_data.convert.Mapper;
import com.elcom.management_library_data.mysql.dto.AuthorDto;
import com.elcom.management_library_data.mysql.model.Author;
import com.elcom.management_library_data.mysql.repository.AuthorRepository;
import com.elcom.management_library_data.mysql.service.AuthorService;
import java.util.ArrayList;
import java.util.List;
import com.elcom.management_library_common.exception.NoSuchElementException;
import com.elcom.management_library_data.convert.VNCharacterUtils;
import com.elcom.management_library_data.mysql.dto.CategoryDto;
import com.elcom.management_library_data.mysql.service.CategoryService;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImp implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    //private static final Logger LOGGER = LoggerFactory.getLogger(AuthorServiceImp.class);

    @Autowired
    Mapper mapper;

    @Autowired
    private RedisTemplate template;
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    VNCharacterUtils vnCharacterUtils;

    @Override
    public List<AuthorDto> getListAuthor() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDto> result = new ArrayList<>();
        if (!authors.isEmpty()) {
            for (Author author : authors) {
                result.add(mapper.toAuthorDto(author));
            }
        }
        return result;
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        AuthorDto authorDto = new AuthorDto();
        Gson g = new Gson();
        if (template.opsForValue().get("author" + id) == null) {
            Author author = authorRepository.findById(id).orElse(null);
            if(author != null){
                authorDto = mapper.toAuthorDto(author);
                String authorSave = g.toJson(authorDto);
                template.opsForValue().set("author" + id, authorSave);
                System.out.println("Lay trong csdl");
                return authorDto;
            }
            throw new NoSuchElementException("Tac gia khong ton tai");
        }
        else{
            String authorGet = template.opsForValue().get("author" + id).toString();
            authorDto = g.fromJson(authorGet, AuthorDto.class);
            System.out.println("Lay trong redis");
            return authorDto;
        }

    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = new Author();
        if (authorDto != null) {
            author = mapper.toAuthor(authorDto);
            author.setCreatedDate(new Date());
            authorRepository.save(author);
        }
        return mapper.toAuthorDto(author);
    }

    @Override
    public AuthorDto updateAuthor(AuthorDto authorDto, Long id) {
        Author oldAuthor = authorRepository.findById(id).orElse(null);
        Author newAuthor = new Author();
        if (oldAuthor != null) {
            newAuthor = mapper.toAuthor(oldAuthor, authorDto);
            newAuthor.setId(id);
            newAuthor.setModifiedDate(new Date());
            authorRepository.save(newAuthor);
            return mapper.toAuthorDto(newAuthor);
        }

        // Ném ra NoSuchElementException
        throw new NoSuchElementException("Tac gia khong ton tai");
    }

    @Override
    public String deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            authorRepository.deleteById(id);
            return "Xoa thanh cong";
        }

        // Ném ra NoSuchElementException
        throw new NoSuchElementException("Tac gia khong ton tai");
    }
    
    @Override
    public List<String> crawlAuthor() {
        String url = "https://nhasachmienphi.com/";
        List<CategoryDto> listCategoryDto = categoryService.getListCategory();
        List<String> categorys = new ArrayList();
        for(CategoryDto ca : listCategoryDto){
            categorys.add(ca.getName());
        }
        List<String> authorNames = new ArrayList();
        for (String category : categorys) {
            String urlCategory = url + "category/" + handleUrl(category);
            try {
                Document dc = Jsoup.connect(urlCategory).get();
                Elements itemSachs = dc.getElementsByClass("item_sach");

                for (Element itemSach : itemSachs) {
                    Element aTag = itemSach.getElementsByTag("a").get(1);
                    String aText = aTag.ownText();
                    if (!aText.equals("")) {
                        String urlBook = url + handleUrl(aText) + ".html";
                        URL urlExist = new URL(urlBook);
                        HttpURLConnection huc = (HttpURLConnection) urlExist.openConnection();
                        huc.setRequestMethod("HEAD");
                        int responseCode = huc.getResponseCode();
                       if (responseCode == 200) {
                            Document dc1 = Jsoup.connect(urlBook).get();
                            Element authorDiv = dc1.getElementsByClass("mg-t-10").get(1);
                            String authorName = authorDiv.ownText();
                            if(authorName.length() > 8){
                                authorName = authorName.substring(9, authorName.length());
                            }
                            else{
                                authorName = authorName.substring(8, authorName.length());
                            }
                            if(!authorNames.contains(authorName)){
                                authorNames.add(authorName);
                            }
                        }

                    }
                }
                
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(AuthorServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        for(String ath : authorNames){
            Author author = new Author();
            author.setCreatedDate(new Date());
            author.setName(ath);
            authorRepository.save(author);
        }
        return authorNames;
    }

    public String handleUrl(String url) {
        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) == ' ') {
                url = url.substring(0, i + 1) + Character.toString(url.charAt(i + 1)).toUpperCase() + url.substring(i + 2, url.length());
            }
        }
        String url1 = url.replace(" ", "");
        url1 = url1.replace(":", "");
        url1 = url1.replace("\b", "");
        url1 = url1.replace("(", "");
        url1 = url1.replace(")", "");
        String url2 = VNCharacterUtils.removeAccent(url1.replace("-", ""));
        for (int i = 1; i < url2.length(); i++) {
            if (url2.charAt(i) < 91 && url2.charAt(i) > 64) {
                url2 = url2.substring(0, i) + "-" + url2.substring(i, url2.length());
                i++;
            }
        }
        return url2.toLowerCase();
    }

}
