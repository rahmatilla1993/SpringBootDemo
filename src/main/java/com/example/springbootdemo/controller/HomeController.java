package com.example.springbootdemo.controller;

import com.example.springbootdemo.config.MailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final MailConfig mailConfig;

    @Autowired
    public HomeController(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
    }

    @GetMapping("/home")
    public String home(Model model) {
        System.out.println(mailConfig);
        model.addAttribute("message", "Some message");
        return "home";
    }
}
