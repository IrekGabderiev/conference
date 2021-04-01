package com.irek.conference.controller;

import com.irek.conference.entity.Presentation;
import com.irek.conference.entity.Room;
import com.irek.conference.entity.Schedule;
import com.irek.conference.entity.User;
import com.irek.conference.repository.PresentationRepository;
import com.irek.conference.service.BadTimeException;
import com.irek.conference.service.PresentationService;
import com.irek.conference.service.RoomService;
import com.irek.conference.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/presentations")
@PreAuthorize("hasAuthority('PRESENTER')")
public class PresentationController {
    @Autowired
    private PresentationService presentationService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private PresentationRepository presentationRepository;

    @GetMapping
    public String getPresentations(@RequestParam(name = "error", required = false) String string, @AuthenticationPrincipal User user, Model model) {
        List<Presentation> presentationList = user.getPresentationList();
        System.out.println("Презентации " + presentationList.size());
        List<Schedule> list = new ArrayList<>();
        if (presentationList.size() > 0) {
            for (Presentation p : presentationList) {
                list.add(scheduleService.findByPresentation(p).orElseThrow());
            }
        }
        if (string != null)
            model.addAttribute("message", "Ваше время презентации пересекается с другим!");
        model.addAttribute("list", list);
        return "presentations";
    }

    @PostMapping("/new")
    public String newPresentation(@AuthenticationPrincipal User user,
                                  @RequestParam(name = "name") String name,
                                  @RequestParam(name = "date") String date,
                                  @RequestParam(name = "time") String time,
                                  @RequestParam(name = "room") String room) {
        List<Presentation> list = user.getPresentationList();
        Presentation presentation = new Presentation(name, user);
        try {
            presentationService.save(presentation, date, time, room);
        } catch (BadTimeException e) {
            return "redirect:/presentations?error=true";
        }
        list.add(presentation);
        return "redirect:/presentations";
    }

    @GetMapping("/delete/{id}")
    public String delete(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        Presentation presentation = presentationRepository.findById(id).orElseThrow();
        user.getPresentationList().remove(presentation);
        presentationRepository.delete(presentation);
        System.out.println("Удаление");
        return "redirect:/presentations";
    }
}
