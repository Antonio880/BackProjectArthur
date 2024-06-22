package com.prototype.ProjectArthur.repository;

import com.prototype.ProjectArthur.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByCreatedBy_Id(Long userId);
}
