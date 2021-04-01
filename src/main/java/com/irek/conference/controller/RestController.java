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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private RoomService roomService;
    ;
    
    @GetMapping("/schedule/{room}")
    public ResponseEntity<List<Schedule>> getSchedule(@PathVariable Integer room){
        Room room1 = roomService.findById(room);
        System.out.println(room);
        return new ResponseEntity<>(room1.getScheduleList(), HttpStatus.OK);
    }
}
