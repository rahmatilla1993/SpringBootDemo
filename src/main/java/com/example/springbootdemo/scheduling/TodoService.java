package com.example.springbootdemo.scheduling;

import com.example.springbootdemo.exception.NotFoundException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TodoService {

    private final JavaMailSender javaMailSender;
    private final Configuration configuration;
    private int genID = 1;
    private final List<Todo> todos = new ArrayList<>(List.of(
            new Todo(genID++, "Read the book", "Reading", false),
            new Todo(genID++, "Have a lunch", "Lunching", false),
            new Todo(genID++, "Running a stadium", "Sport", true)
    ));

    @Autowired
    public TodoService(JavaMailSender javaMailSender, Configuration configuration) {
        this.javaMailSender = javaMailSender;
        this.configuration = configuration;
    }

    public List<Todo> getAll() {
        return todos;
    }

    public Todo getOne(int id) {
        return todos
                .stream()
                .filter(todo -> todo.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Todo with id '%d' not found".formatted(id)));
    }

    public boolean save(TodoDto dto) {
        return todos.add(
                Todo.builder()
                        .title(dto.getTitle())
                        .category(dto.getCategory())
                        .id(genID++)
                        .isDone(false)
                        .build()
        );
    }

    public void setDone(int id) {
        Todo todo = getOne(id);
        todo.setDone(!todo.isDone());
    }

    public void edit(TodoDto dto, int id) {
        Todo todo = getOne(id);
        todo.setTitle(dto.getTitle());
        todo.setCategory(dto.getCategory());
    }

    public boolean delete(int id) {
        return todos.remove(getOne(id));
    }

    @Async
//    @Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "0 0 8,21 * * *")
    public void sendUnFinishedTodos() {
        List<Todo> todoList = todos
                .stream()
                .filter(todo -> !todo.isDone())
                .toList();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("from@mail.ru");
            messageHelper.setTo("to@gmail.com");
            messageHelper.setSubject("Unfinished todos");
            Template template = configuration.getTemplate("todos.ftlh");
            var map = Map.of("todos", todoList);
            String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
            messageHelper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
