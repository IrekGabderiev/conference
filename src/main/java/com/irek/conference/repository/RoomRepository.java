package com.irek.conference.repository;

import com.irek.conference.entity.Room;
import com.irek.conference.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

}
