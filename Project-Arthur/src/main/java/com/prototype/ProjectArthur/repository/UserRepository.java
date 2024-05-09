package com.prototype.ProjectArthur.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prototype.ProjectArthur.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String login);
}
