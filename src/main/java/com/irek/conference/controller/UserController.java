package com.irek.conference.controller;


import com.irek.conference.entity.Role;
import com.irek.conference.entity.User;
import com.irek.conference.repository.UserRepository;
import com.irek.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String userList(Model model){
        model.addAttribute("userList", userService.userList());
        return "userList";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return "redirect:/users";
}

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id)
    {
        User user = userRepository.findById(id).orElseThrow();
        if(user.getRole() == Role.LISTENER) {
            user.setRole(Role.PRESENTER);
            userRepository.save(user);
        }
        else if(user.getRole() == Role.PRESENTER){
            user.setRole(Role.LISTENER);
            userRepository.save(user);
        }

        return "redirect:/users";
    }



}
