package com.example.schoolsite.controllers;

import com.example.schoolsite.entity.User;
import com.example.schoolsite.workWithDatabase.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "blog-main";
    }
}
