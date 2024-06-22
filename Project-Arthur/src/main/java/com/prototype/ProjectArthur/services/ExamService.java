package com.prototype.ProjectArthur.services;

import com.prototype.ProjectArthur.DTO.ExamDTO;
import com.prototype.ProjectArthur.model.Exam;
import com.prototype.ProjectArthur.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamService {

    @Autowired
    private ExamRepository repository;

    public List<ExamDTO> getAllExams() {
        return convertDataToDTOs(repository.findAll());
    }

    public ExamDTO getExamById(Long id) {
        Exam exam = repository.findById(id).orElse(null);
        if (exam != null) {
            return convertData(exam);
        } else {
            return null;
        }
    }

    public ExamDTO postExam(Exam entity) {
        return convertData(repository.save(entity));
    }

    public ExamDTO updateExam(Long id, Exam entity) {
        Exam existingExam = repository.findById(id).orElse(null);
        if (existingExam != null) {
            entity.setId(id);
            return convertData(repository.save(entity));
        } else {
            return null;
        }
    }

    public void deleteExam(Long id) {
        repository.deleteById(id);
    }

    private List<ExamDTO> convertDataToDTOs(List<Exam> exams) {
        return exams.stream()
                .map(this::convertData)
                .collect(Collectors.toList());
    }

    private ExamDTO convertData(Exam exam) {
        // Implement conversion logic
        return new ExamDTO(exam.getId(), exam.getCategory(), exam.getRoom().getId());
    }
}
