package com.irek.conference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginError(@RequestParam(required = false,name = "error") String error, Model model){
        if(error != null) {
            model.addAttribute("error", "Неправильный пароль или логин");
        }
        return "login";
    }
}
