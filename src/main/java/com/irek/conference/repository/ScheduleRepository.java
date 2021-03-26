package com.irek.conference.repository;

import com.irek.conference.entity.Presentation;
import com.irek.conference.entity.Room;
import com.irek.conference.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findAllByRoom(Room room);
    List<Schedule> findAllByPresentation(Presentation presentation);
}
