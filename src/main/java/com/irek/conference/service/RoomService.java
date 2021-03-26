package com.irek.conference.service;

import com.irek.conference.entity.Room;
import com.irek.conference.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    public void save(Room room){
        roomRepository.save(room);
    }
    public List<Room> roomList(){
        return roomRepository.findAll();
    }
    public Room findById(Integer id){
        Optional<Room> optionalRoom= roomRepository.findById(id);
        return optionalRoom.orElseThrow();
    }
}
