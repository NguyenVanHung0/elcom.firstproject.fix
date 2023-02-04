package com.elcom.management_library_common.scheduling;

import com.elcom.management_library_data.postgresql.repository.BorrowLogRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {

    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

    @Autowired
    BorrowLogRepository borrowLogRepository;

    @Scheduled(cron = "0 0 7 * * *")
    //@Scheduled(fixedRate = 5000)
    public void sendEmail() {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage mailMessage;
        Date today = new Date();
        Date yesterday = new Date(today.getTime() - MILLIS_IN_A_DAY);

        // setup mail server.
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        //get mail session
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);
        try {
            mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("nguyenhung27112001@gmail.com"));
            mailMessage.setSubject("Bao cao so luong muon sach ngay: " + yesterday);
            mailMessage.setText("So sach duoc muon la: " + countBrorrowLogLastDay());
            // send email
            Transport transport = getMailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", "hungnguyen27112001@gmail.com", "rtsojddnoatxvduz");
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            transport.close();
        } catch (AddressException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Long countBrorrowLogLastDay() {
        Date today = new Date();
        Date yesterday = new Date(today.getTime() - MILLIS_IN_A_DAY);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Long count = 0L;
        try {
            count = borrowLogRepository.countNumberOfBorrowLogLastDay(sdf.parse(sdf.format(yesterday)));
        } catch (ParseException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;

    }

}
