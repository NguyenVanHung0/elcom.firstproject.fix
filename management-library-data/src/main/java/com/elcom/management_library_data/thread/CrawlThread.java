package com.elcom.management_library_data.thread;

import com.elcom.management_library_data.mysql.model.DetailBook;
import com.elcom.management_library_data.mysql.repository.DetailBookRepository;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CrawlThread extends Thread {

    private List<String> urls;

    
    private DetailBookRepository detailBookRepository;

    @Override
    public void run() {
        System.out.println("thread running");
        for (String url : urls) {
            String description = "";
            
            try {
                Document dc = Jsoup.connect(url).get();
                Element Div = dc.getElementsByClass("gioi_thieu_sach").get(0);
                Elements P = Div.getElementsByTag("p");
                for (Element p : P) {
                    description += p.ownText();
                }
            } catch (IOException ex) {
                Logger.getLogger(CrawlThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            DetailBook detailBook = new DetailBook();
            detailBook.setDescription(description);
            detailBook.setBookName(url.substring(27).substring(0,url.substring(27).length()-4));
            detailBookRepository.save(detailBook);
        }

    }

}
