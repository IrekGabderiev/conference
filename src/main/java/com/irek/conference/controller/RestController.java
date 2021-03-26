package com.irek.conference.controller;

import com.irek.conference.entity.Role;
import com.irek.conference.entity.Room;
import com.irek.conference.entity.Schedule;
import com.irek.conference.entity.User;
import com.irek.conference.repository.UserRepository;
import com.irek.conference.service.RoomService;
import com.irek.conference.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/schedule/{room}")
    public ResponseEntity<List<Schedule>> getSchedule(@PathVariable Integer room){
        Room room1 = roomService.findById(room);
        System.out.println(room);
        return new ResponseEntity<>(room1.getScheduleList(), HttpStatus.OK);
    }
    
    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable Integer id)        
    {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    @GetMapping("/users/edit/{id}")
    public void editUser(@PathVariable Integer id)
    {
        User user = userRepository.findById(id).orElseThrow();
        if(user.getRole() == Role.LISTENER)
            userRepository.changeRole(Role.PRESENTER.name(), id);
        else if(user.getRole() == Role.PRESENTER)
            userRepository.changeRole(Role.LISTENER.name(), id);
    }
}
