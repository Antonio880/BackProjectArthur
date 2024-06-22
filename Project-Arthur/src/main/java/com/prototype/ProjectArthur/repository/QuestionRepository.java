package com.prototype.ProjectArthur.repository;

import com.prototype.ProjectArthur.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
