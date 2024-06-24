package com.prototype.ProjectArthur.repository;

import com.prototype.ProjectArthur.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByCreatedBy_Id(Long userId);
    @Query("SELECT r FROM Room r JOIN FETCH r.users WHERE r.id = :roomId")
    Optional<Room> findByIdWithStudents(@Param("roomId") Long roomId);
}
