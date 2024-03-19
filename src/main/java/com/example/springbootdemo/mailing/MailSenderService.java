package com.example.springbootdemo.mailing;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Service
public class MailSenderService {

    private final JavaMailSender javaMailSender;
    private final Configuration configuration;

    @Autowired
    public MailSenderService(JavaMailSender javaMailSender,
                             Configuration configuration) {
        this.javaMailSender = javaMailSender;
        this.configuration = configuration;
    }

    @Async
    public void sendFreeMarkerMail(String username) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("from@mail.ru");
            messageHelper.setTo("to@gmail.com");
            messageHelper.setSubject("Freemarker example");
            Template template = configuration.getTemplate("main.ftlh");
            String token = Base64.getEncoder().encodeToString(username.getBytes());
            var map = Map.of(
                    "username", username,
                    "token", token
            );
            String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
            messageHelper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
        } catch (IOException | TemplateException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
