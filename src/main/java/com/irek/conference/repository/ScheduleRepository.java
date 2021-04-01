package com.irek.conference.repository;

import com.irek.conference.entity.Presentation;
import com.irek.conference.entity.Room;
import com.irek.conference.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findAllByRoom(Room room);
    Optional<Schedule> findAllByPresentation(Presentation presentation);
    List<Schedule> findAllByRoomAndDate(Room room, Date date);
}
