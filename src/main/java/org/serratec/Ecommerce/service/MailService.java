package org.serratec.Ecommerce.service;

import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailService {
    private JavaMailSender javaMailSender;
    private String from;
    private Session session;

    public MailService(JavaMailSender javaMailSender, @Value("${spring.mail.username}") String from, @Value("${spring.mail.host}") String host, @Value("${spring.mail.port}") String port, @Value("${spring.mail.username}") String username, @Value("${spring.mail.password}") String password) {
        this.javaMailSender = javaMailSender;
        this.from = from;

        Properties props = new Properties();
        props.put("spring.mail.host", host);
        props.put("spring.mail.port", port);
        props.put("spring.mail.username", username);
        props.put("spring.mail.password", password);
        session = Session.getInstance(props);
    }

    // @Async permite que o enviarMensagem não trave requisições simples que demorariam <1seg
    @Async
    public void enviarMensagem(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        this.javaMailSender.send(message);
    }
}
