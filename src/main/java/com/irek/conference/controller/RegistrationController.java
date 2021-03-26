package com.irek.conference.controller;


import com.irek.conference.entity.Role;
import com.irek.conference.entity.User;
import com.irek.conference.repository.UserRepository;
import com.irek.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam(name="login") String login,@RequestParam(name="password") String password, Model model){
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(passwordEncoder.encode(password));
        User userFromDb = userService.findByLogin(newUser.getLogin());
        if(userFromDb != null) {
            model.addAttribute("message", "Существует");
            return "registration";
        }
        userService.newUser(newUser);
        return "redirect:/login";
    }
}
