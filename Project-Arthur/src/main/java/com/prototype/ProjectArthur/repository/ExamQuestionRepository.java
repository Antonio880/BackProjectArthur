package com.prototype.ProjectArthur.repository;
import com.prototype.ProjectArthur.model.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Long> {
    List<ExamQuestion> findByExamId(Long examId);
}

