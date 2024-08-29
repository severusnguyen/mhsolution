
package com.mh.core.mhsapi.service.api.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


/**
 * Created by DucDV on 17-03-2022
 * HN, VN.
 */

public class YandexEmailUtil {

    public static void sendFromYandexNoreply(String to, String subject, String body) throws MessagingException {
        String fromEmail = "noreply@mhsolution.vn";
        String password = "Mh#@!123";
        sendFromYandex(to, subject, body, fromEmail, password);
    }

    public static void sendFromYandexHr(String to, String subject, String body) throws MessagingException {
        String fromEmail = "hr@mhsolution.vn";
        String password = "123456aA";
        sendFromYandex(to, subject, body, fromEmail, password);
    }

    public static void sendFromYandex(String to, String subject, String body, String fromEmail, String password) throws MessagingException {
        Properties props = System.getProperties();
        String host = "smtp.yandex.com";
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", fromEmail);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.quitwait", "false");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.debug", "true");

        Session session = Session.getInstance(props);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setHeader("Content-Type", "text/plain; charset=UTF-8");

        try {
            message.setFrom(new InternetAddress(fromEmail));
            InternetAddress toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(subject);
            message.setContent(body, "text/html; charset=utf-8");
            message.setSentDate(new Date());

            Transport transport = session.getTransport("smtp");
            transport.connect(host, fromEmail, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException ignored) {

        }
    }
}

