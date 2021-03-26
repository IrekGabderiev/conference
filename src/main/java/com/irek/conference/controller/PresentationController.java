package com.irek.conference.controller;

import com.irek.conference.entity.Presentation;
import com.irek.conference.entity.Room;
import com.irek.conference.entity.Schedule;
import com.irek.conference.entity.User;
import com.irek.conference.repository.PresentationRepository;
import com.irek.conference.service.PresentationService;
import com.irek.conference.service.RoomService;
import com.irek.conference.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PresentationController {
    @Autowired
    private PresentationService presentationService;
    @Autowired
    private ScheduleService scheduleService;
    @GetMapping(value="/presentations")
    public String getPresentations(@AuthenticationPrincipal User user, Model model){
        List<Presentation> presentationList = user.getPresentationList();
        List<Schedule> list = new ArrayList<>();
        for (Presentation p:presentationList) {
            list.add(scheduleService.findByPresentation(p).get(0));
        }
        model.addAttribute("list", list);
        return "presentations";
    }

    @PostMapping("/presentations")
    public String newPresentation(@AuthenticationPrincipal User user,
                                  @RequestParam (name="name") String name,
                                  @RequestParam (name="date") String date,
                                  @RequestParam (name="time") String time,
                                  @RequestParam (name="room") String room,
                                  Model model){
        List<Presentation> list = user.getPresentationList();
        model.addAttribute("list", list);
        Presentation presentation = new Presentation(name,user);
        presentationService.save(presentation, date, time, room);
        list.add(presentation);
        return "presentations";
    }
}
