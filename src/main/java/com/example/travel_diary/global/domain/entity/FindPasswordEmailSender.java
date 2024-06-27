package com.example.travel_diary.global.domain.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Configuration
@Component
public class FindPasswordEmailSender {
    @Value("${mail.sender}")
    private String mailSender;

    @Value("${mail.password}")
    private String mailPassword;

    public void emailSender(String nickname, String email, String loginPassword) throws javax.mail.MessagingException {
        String to = email; // 받는 사람의 이메일 주소
        String from = mailSender; // 보내는 사람의 이메일 주소
        String password = mailPassword; // 보내는 사람의 이메일 계정 비밀번호
        String host = "smtp.gmail.com"; // 구글 메일 서버 호스트 이름

        // SMTP 프로토콜 설정
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");

        // 보내는 사람 계정 정보 설정
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // 메일 내용 작성
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject("JORANG입니다");
        String emailContent = nickname + "님의 임시 비밀번호는 " + loginPassword +" 입니다.";
        msg.setText(emailContent);
        // 메일 보내기
        Transport.send(msg);

    }
}
