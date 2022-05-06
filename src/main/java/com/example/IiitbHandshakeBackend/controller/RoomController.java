package com.example.IiitbHandshakeBackend.controller;

import com.example.IiitbHandshakeBackend.entity.Room;
import com.example.IiitbHandshakeBackend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/addRoom")
    public ResponseEntity<?> addRoom(@RequestBody Room room){
        try{
            room = roomService.addRoom(room);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(room);
    }

    @GetMapping("/getRooms")
    public ResponseEntity<?> getRooms(){
        return ResponseEntity.ok(roomService.getRooms());
    }
}
