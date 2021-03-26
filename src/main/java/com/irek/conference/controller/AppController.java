package com.irek.conference.controller;

import com.irek.conference.entity.Presentation;
import com.irek.conference.entity.Room;
import com.irek.conference.entity.Schedule;
import com.irek.conference.entity.User;
import com.irek.conference.service.PresentationService;
import com.irek.conference.service.RoomService;
import com.irek.conference.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private PresentationService presentationService;

    @GetMapping
    public String getIndex(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("roomList", roomService.roomList());
        return "index";
    }

}
