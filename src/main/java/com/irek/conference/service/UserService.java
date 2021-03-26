package com.irek.conference.service;

import com.irek.conference.entity.Role;
import com.irek.conference.entity.User;
import com.irek.conference.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if(user == null){
            throw new UsernameNotFoundException("User not authorized");
        }
        return user;
    }
    public Iterable<User> userList(){
        return userRepository.findAll();
    }

    public void newUser(User user){

        user.setRole(Role.LISTENER);
        user.setEnabled(true);
        userRepository.save(user);
        System.out.println("Save user");

    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public User findbyId(Integer id){
        return userRepository.findById(id).orElseThrow();
    }
}
