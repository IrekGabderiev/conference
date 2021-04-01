package com.irek.conference.service;

import com.irek.conference.entity.Presentation;
import com.irek.conference.entity.Room;
import com.irek.conference.entity.Schedule;
import com.irek.conference.entity.User;
import com.irek.conference.repository.PresentationRepository;
import com.irek.conference.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
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
    @Autowired
    private ScheduleRepository scheduleRepository;
    public Optional<Presentation> getPresentationById(int id){
        return presentationRepository.findById(id);
    }

    public void save(Presentation presentation, String date, String time, String room) throws BadTimeException {
        Room room1 = roomService.findById(Integer.valueOf(room));
        time += ":00";
        Date date1 = Date.valueOf(date);
        Time time1 = Time.valueOf(time);
        Schedule schedule = new Schedule(date1, time1, room1, presentation);
        List<Schedule> scheduleList = scheduleRepository.findAllByRoomAndDate(room1, date1);
        LocalTime timeOtherPresentation;
        LocalTime timeThisPresentation = time1.toLocalTime();
        for (Schedule sc:scheduleList) {
            timeOtherPresentation = sc.getStart_time().toLocalTime();
            if(timeThisPresentation.plusHours(2).compareTo(timeOtherPresentation) > 0 //Принимаем что одна презентация идет 2 часа
             || timeThisPresentation.compareTo(timeOtherPresentation.plusHours(2)) < 0)
                throw new BadTimeException();
        }
        scheduleService.save(schedule);
    }

}
