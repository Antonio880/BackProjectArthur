package com.prototype.ProjectArthur.services;

import com.prototype.ProjectArthur.DTO.RoomDTO;
import com.prototype.ProjectArthur.DTO.UserDTO;
import com.prototype.ProjectArthur.model.Room;
import com.prototype.ProjectArthur.model.User;
import com.prototype.ProjectArthur.repository.RoomRepository;
import com.prototype.ProjectArthur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    public List<RoomDTO> getAllRooms() {
        return convertDataToDTOs(roomRepository.findAll());
    }

    public RoomDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            return convertData(room);
        } else {
            return null;
        }
    }

    private List<UserDTO> convertDataToDTOsUser(List<User> users) {
        return users.stream()
                .map(this::convertDataUser)
                .collect(Collectors.toList());
    }

    private UserDTO convertDataUser(User user) {
        Long roomId = user.getRoom() != null ? user.getRoom().getId() : null;
        return new UserDTO(user.getId(), user.getEmail(), user.getUsername(), user.getPassword(), user.getRole(), roomId);
    }

    public RoomDTO postRoom(Room entity, Long creatorId) {
        User creator = userRepository.findById(creatorId).orElse(null);
        if (creator == null) {
            throw new RuntimeException("Professor not found");
        }

        entity.setCreatedBy(creator);
        if (entity.getStudents() == null) {
            entity.setStudents(new HashSet<>());
        }
        Room savedRoom = roomRepository.save(entity);

        creator.setRoom(savedRoom);
        userRepository.save(creator);

        return convertData(savedRoom);
    }

    public RoomDTO updateRoom(Long id, Room entity) {
        Room existingRoom = roomRepository.findById(id).orElse(null);
        if (existingRoom != null) {
            entity.setId(id);
            return convertData(roomRepository.save(entity));
        } else {
            return null;
        }
    }

    public List<UserDTO> getStudentsByRoomId(Long roomId) {
        Room room = roomRepository.findByIdWithStudents(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        return room.getStudents().stream()
                .map(this::convertDataUser)
                .collect(Collectors.toList());
    }

    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            for (User student : room.getStudents()) {
                student.setRoom(null);
                userRepository.save(student);
            }
            roomRepository.delete(room);
        }
    }

    public RoomDTO addUserToRoom(Long roomId, Long userId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (room != null && user != null) {
            user.setRoom(room);
            userRepository.save(user);
            return convertData(room);
        } else {
            return null;
        }
    }

    private List<RoomDTO> convertDataToDTOs(List<Room> rooms) {
        return rooms.stream()
                .map(this::convertData)
                .collect(Collectors.toList());
    }

    private RoomDTO convertData(Room room) {
        Long createdById = room.getCreatedBy() != null ? room.getCreatedBy().getId() : null;
        Set<Long> userIds = room.getStudents().stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        return new RoomDTO(room.getId(), room.getSerie(), room.getCurso(), createdById, userIds);
    }

    public List<RoomDTO> getRoomsByCreatorId(Long creatorId) {
        List<Room> rooms = roomRepository.findByCreatedBy_Id(creatorId);
        return convertDataToDTOs(rooms);
    }
}
