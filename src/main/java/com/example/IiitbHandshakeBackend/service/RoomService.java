package com.example.IiitbHandshakeBackend.service;

import com.example.IiitbHandshakeBackend.entity.Room;
import com.example.IiitbHandshakeBackend.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepo roomRepo;

    public Room addRoom(Room room){
        room.setAvailable(true);
        return roomRepo.save(room);
    }

    public List<Room> getRooms(){
        return roomRepo.findAll();
    }
}
