package com.prototype.ProjectArthur.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prototype.ProjectArthur.repository.UserRepository;
import com.prototype.ProjectArthur.DTO.UserDTO;
import com.prototype.ProjectArthur.model.User;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public List<UserDTO> getAllUsers(){
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
            entity.setId(id); // Ensure the ID is set correctly
            return convertData(repository.save(entity));
        } else {
            return null;
        }
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
        return new UserDTO(user.getId(), user.getEmail(), user.getUsername(), user.getPassword());
    }
}
