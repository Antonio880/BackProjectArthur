package com.prototype.ProjectArthur.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prototype.ProjectArthur.model.Room;
/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/

import com.prototype.ProjectArthur.repository.UserRepository;
import com.prototype.ProjectArthur.DTO.UserDTO;
import com.prototype.ProjectArthur.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> getAllUsers() {
        return convertDataToDTOs(repository.findAll());
    }

    public UserDTO getUserById(Long id) {
        User user = repository.findById(id).orElse(null);
        if (user != null) {
            return convertData(user);
        } else {
            return null;
        }
    }

    public UserDTO postUser(User entity) {
        return convertData(repository.save(entity));
    }

    public UserDTO updateUser(Long id, User entity) {
        User existingUser = repository.findById(id).orElse(null);
        if (existingUser != null) {
            entity.setId(id);
            entity.setRole(existingUser.getRole());
            entity.setRoom(existingUser.getRoom());
            return convertData(repository.save(entity));
        } else {
            return null;
        }
    }

    public Room getRoomByStudentId(Long userId) {
        return repository.findRoomByUserId(userId);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    private List<UserDTO> convertDataToDTOs(List<User> users) {
        return users.stream()
                .map(this::convertData)
                .collect(Collectors.toList());
    }

    private UserDTO convertData(User user) {
        Long roomId = user.getRoom() != null ? user.getRoom().getId() : null;
        return new UserDTO(user.getId(), user.getEmail(), user.getUsername(), user.getPassword(), user.getRole(), roomId);
    }

    public UserDTO loginUser(User entity) {
        User user = repository.findByEmailAndPassword(entity.getEmail(), entity.getPassword());
        if (user != null) {
            return convertData(user);
        } else {
            return null;
        }
    }
}
