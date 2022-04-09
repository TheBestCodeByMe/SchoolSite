package com.example.schoolsite.controllers;

import com.example.schoolsite.entity.User;
import com.example.schoolsite.workWithDatabase.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String registration(@RequestParam String login, @RequestParam String pass, @RequestParam String role, Model model) {
        User user = new User(login, pass, role);
        userRepository.save(user);
        return "redirect:/blog";
    }

    // роль, отслеживать так роль
    // или лучше ФИО, чтобы страница была этого человека
    @GetMapping("/blog/{id}")
    public String blogDetails(Model model, @PathVariable(value = "id") long id) {
        if(!userRepository.existsById(id)){
            return "redirect:/blog";
        }

        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add); // всё, что в оптионал приводим в рес
        model.addAttribute("post", res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(Model model, @PathVariable(value = "id") long id) {
        if(!userRepository.existsById(id)){
            return "redirect:/blog";
        }

        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add); // всё, что в оптионал приводим в рес
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String update(@PathVariable(value = "id") long id, @RequestParam String login, @RequestParam String pass, @RequestParam String role, Model model) {
        User user = userRepository.findById(id).orElseThrow(); // orElseThrow - если такого нет
        user.setLogin(login);
        user.setPassword(pass);
        //user.setRole(role);
        userRepository.save(user);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String delete(@PathVariable(value = "id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(); // orElseThrow - если такого нет
        userRepository.delete(user);
        return "redirect:/blog";
    }
}
