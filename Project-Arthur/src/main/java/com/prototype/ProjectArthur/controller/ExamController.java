package com.prototype.ProjectArthur.controller;

import com.prototype.ProjectArthur.DTO.ExamDTO;
import com.prototype.ProjectArthur.model.Exam;
import com.prototype.ProjectArthur.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping
    public List<ExamDTO> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{id}")
    public ExamDTO getExamById(@PathVariable Long id) {
        return examService.getExamById(id);
    }

    @PostMapping
    public ExamDTO createExam(@RequestBody Exam exam) {
        return examService.postExam(exam);
    }

    @PutMapping("/{id}")
    public ExamDTO updateExam(@PathVariable Long id, @RequestBody Exam exam) {
        return examService.updateExam(id, exam);
    }

    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
    }
}

