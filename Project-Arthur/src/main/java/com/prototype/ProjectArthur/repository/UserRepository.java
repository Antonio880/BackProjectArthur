package com.prototype.ProjectArthur.repository;

import com.prototype.ProjectArthur.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prototype.ProjectArthur.model.User;
import org.springframework.data.jpa.repository.Query;
/*
import org.springframework.security.core.userdetails.UserDetails;
*/

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
    /*UserDetails findByUsername(String login);*/
    @Query("SELECT r FROM Room r JOIN r.users u WHERE u.id = :userId")
    Room findRoomByUserId(Long userId);
}
