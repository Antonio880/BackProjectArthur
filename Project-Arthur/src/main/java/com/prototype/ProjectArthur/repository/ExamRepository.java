package com.prototype.ProjectArthur.repository;

import com.prototype.ProjectArthur.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByCreatedById(Long createdById);
    List<Exam> findByRoomId(Long roomId);
}