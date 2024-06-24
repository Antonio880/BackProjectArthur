package com.prototype.ProjectArthur.controller;

import com.prototype.ProjectArthur.DTO.RoomDTO;
import com.prototype.ProjectArthur.DTO.UserDTO;
import com.prototype.ProjectArthur.model.Room;
import com.prototype.ProjectArthur.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public RoomDTO getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @PostMapping
    public RoomDTO createRoom(@RequestBody Room room, @RequestParam Long creatorId) {
        return roomService.postRoom(room, creatorId);
    }

    @GetMapping("/createdBy/{creatorId}")
    public List<RoomDTO> getRoomsByCreatorId(@PathVariable Long creatorId) {
        return roomService.getRoomsByCreatorId(creatorId);
    }

    @PutMapping("/{id}")
    public RoomDTO updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }

    @PostMapping("/{roomId}/users/{userId}")
    public RoomDTO addUserToRoom(@PathVariable Long roomId, @PathVariable Long userId) {
        return roomService.addUserToRoom(roomId, userId);
    }

    @GetMapping("/{roomId}/students")
    public List<UserDTO> getStudentsByRoomId(@PathVariable Long roomId) {
        return roomService.getStudentsByRoomId(roomId);
    }
}
