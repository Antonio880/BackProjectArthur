package com.prototype.ProjectArthur.services;

import com.prototype.ProjectArthur.DTO.RoomDTO;
import com.prototype.ProjectArthur.model.Room;
import com.prototype.ProjectArthur.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    public List<RoomDTO> getAllRooms() {
        return convertDataToDTOs(repository.findAll());
    }

    public RoomDTO getRoomById(Long id) {
        Room room = repository.findById(id).orElse(null);
        if (room != null) {
            return convertData(room);
        } else {
            return null;
        }
    }

    public RoomDTO postRoom(Room entity) {
        return convertData(repository.save(entity));
    }

    public RoomDTO updateRoom(Long id, Room entity) {
        Room existingRoom = repository.findById(id).orElse(null);
        if (existingRoom != null) {
            entity.setId(id);
            return convertData(repository.save(entity));
        } else {
            return null;
        }
    }

    public void deleteRoom(Long id) {
        repository.deleteById(id);
    }

    private List<RoomDTO> convertDataToDTOs(List<Room> rooms) {
        return rooms.stream()
                .map(this::convertData)
                .collect(Collectors.toList());
    }

    private RoomDTO convertData(Room room) {
        // Implement conversion logic
        return new RoomDTO(room.getId(), room.getSerie(), room.getCurso(), room.getCreatedBy().getId());
    }
}
