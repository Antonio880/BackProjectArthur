package com.prototype.ProjectArthur.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prototype.ProjectArthur.DTO.UserDTO;
import com.prototype.ProjectArthur.model.User;
import com.prototype.ProjectArthur.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
       try{
           List<UserDTO> users = service.getAllUsers();
           return ResponseEntity.ok(users);
       }catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserDTO userDTO = service.getUserById(id);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para o ID fornecido");
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> postUser(@RequestBody User entity)  {
        UserDTO createdUser = service.postUser(entity);
/*
        var authenticationToken = new UsernamePasswordAuthenticationToken(entity.getEmail(), entity.getPassword());
        System.out.println(authenticationToken);
        var authentication = manager.authenticate(authenticationToken);
        System.out.println(authentication);
        var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());
        System.out.println("TOKEN GERADO NO LOGIN:" + tokenJWT);

        UserResponseDTO response = new UserResponseDTO(, new TokenJWT(tokenJWT));*/
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody User entity) {
        UserDTO loginUser = service.loginUser(entity);
        if(loginUser != null) {
            return ResponseEntity.ok(loginUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(loginUser);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User entity) {
        UserDTO updatedUser = service.updateUser(id, entity);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para o ID fornecido");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
