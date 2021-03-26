package com.irek.conference.service;

import com.irek.conference.entity.Presentation;
import com.irek.conference.entity.Room;
import com.irek.conference.entity.Schedule;
import com.irek.conference.entity.User;
import com.irek.conference.repository.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
public class PresentationService {
    @Autowired
    private PresentationRepository presentationRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ScheduleService scheduleService;
    public Optional<Presentation> getPresentationById(int id){
        return presentationRepository.findById(id);
    }

    public void save(Presentation presentation, String date, String time, String room){
        Room room1 = roomService.findById(Integer.valueOf(room));

        presentationRepository.save(presentation);
        time += ":00";
        Schedule schedule = new Schedule(Date.valueOf(date), Time.valueOf(time), room1, presentation);
        scheduleService.save(schedule);
    }

}
