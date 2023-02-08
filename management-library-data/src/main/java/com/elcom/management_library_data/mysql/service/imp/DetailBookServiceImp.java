
package com.elcom.management_library_data.mysql.service.imp;

//import com.elcom.management_library_api.controller.DetailBookController;
import com.elcom.management_library_data.convert.VNCharacterUtils;
import com.elcom.management_library_data.mysql.dto.CategoryDto;
import com.elcom.management_library_data.mysql.repository.DetailBookRepository;
import com.elcom.management_library_data.mysql.service.CategoryService;
import com.elcom.management_library_data.mysql.service.DetailBookService;
import com.elcom.management_library_data.thread.CrawlThread;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailBookServiceImp implements DetailBookService{
    
    @Autowired
    VNCharacterUtils vnCharacterUtils;
    
    @Autowired
    AuthorServiceImp authorServiceImp;
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    DetailBookRepository detailBookRepository;
    
    @Override
    public List<String> crawlDetailBook(){
        String url = "https://nhasachmienphi.com/";
        List<CategoryDto> listCategoryDto = categoryService.getListCategory();
        List<String> categorys = new ArrayList();
        for(CategoryDto ca : listCategoryDto){
            categorys.add(ca.getName());
        }
        List<String> urlBooks = new ArrayList();
        for (String category : categorys) {
            String urlCategory = url + "category/" + authorServiceImp.handleUrl(category);
            try {
                Document dc = Jsoup.connect(urlCategory).get();
                Elements itemSachs = dc.getElementsByClass("item_sach");

                for (Element itemSach : itemSachs) {
                    Element aTag = itemSach.getElementsByTag("a").get(1);
                    String aText = aTag.ownText();
                    if (!aText.equals("")) {
                        String urlBook = url + authorServiceImp.handleUrl(aText) + ".html";
                        URL urlExist = new URL(urlBook);
                        HttpURLConnection huc = (HttpURLConnection) urlExist.openConnection();
                        huc.setRequestMethod("HEAD");
                        int responseCode = huc.getResponseCode();
                        if(responseCode == 200){
                            urlBooks.add(urlBook);
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(DetailBookServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int index = urlBooks.size() / 5;
        List<String> list1 = urlBooks.subList(0, index);
        CrawlThread crawlThread1 = new CrawlThread();
        crawlThread1.setUrls(list1);
        crawlThread1.setDetailBookRepository(detailBookRepository);
        crawlThread1.start();
        
        List<String> list2 = urlBooks.subList(index, index*2);
        CrawlThread crawlThread2 = new CrawlThread();
        crawlThread2.setUrls(list2);
        crawlThread2.setDetailBookRepository(detailBookRepository);
        crawlThread2.start();
        
        List<String> list3 = urlBooks.subList(index*2, index*3);
        CrawlThread crawlThread3 = new CrawlThread();
        crawlThread3.setUrls(list3);
        crawlThread3.setDetailBookRepository(detailBookRepository);
        crawlThread3.start();
        
        List<String> list4 = urlBooks.subList(index*3, index*4);
        CrawlThread crawlThread4 = new CrawlThread();
        crawlThread4.setUrls(list4);
        crawlThread4.setDetailBookRepository(detailBookRepository);
        crawlThread4.start();
        
        List<String> list5 = urlBooks.subList(index*4, index*5);
        CrawlThread crawlThread5 = new CrawlThread();
        crawlThread5.setUrls(list5);
        crawlThread5.setDetailBookRepository(detailBookRepository);
        crawlThread5.start();
        
        return urlBooks;
    }
}
