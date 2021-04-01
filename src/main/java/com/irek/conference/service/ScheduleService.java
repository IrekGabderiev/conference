package com.irek.conference.service;

import com.irek.conference.entity.Presentation;
import com.irek.conference.entity.Room;
import com.irek.conference.entity.Schedule;
import com.irek.conference.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public void save(Schedule schedule){
        scheduleRepository.save(schedule);
    }

    public List<Schedule> findAll(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> findByRoom(Room room){
        return scheduleRepository.findAllByRoom(room);
    }

    public Optional<Schedule> findByPresentation(Presentation presentation){
        return scheduleRepository.findAllByPresentation(presentation);
    }
}
