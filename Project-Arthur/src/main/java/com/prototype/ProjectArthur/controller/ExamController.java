package com.prototype.ProjectArthur.controller;

import com.prototype.ProjectArthur.DTO.ExamDTO;
import com.prototype.ProjectArthur.DTO.ExamResultDTO;
import com.prototype.ProjectArthur.DTO.QuestionDTO;
import com.prototype.ProjectArthur.DTO.StudentAnswerDTO;
import com.prototype.ProjectArthur.model.Exam;
import com.prototype.ProjectArthur.repository.ExamRepository;
import com.prototype.ProjectArthur.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamRepository examRepository;

    @PostMapping("/create")
    public ExamDTO createExam(@RequestParam String category, @RequestParam int questionCount, @RequestParam Long roomId, @RequestParam Long createdById) {
        return examService.createExam(category, questionCount, roomId, createdById);
    }

    @PostMapping("/{examId}/generate-questions")
    public ResponseEntity<QuestionDTO[]> generateQuestions(@PathVariable Long examId) {
        Exam exam = examRepository.getById(examId);
        if (exam == null) {
            return ResponseEntity.notFound().build();
        }
        QuestionDTO[] questions = examService.generateQuestions(exam.getCategory(), exam.getQuestionCount());
        examService.saveGeneratedQuestions(examId, questions);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/createdBy/{createdById}")
    public List<ExamDTO> getExamsByCreatedById(@PathVariable Long createdById) {
        return examService.getExamsByCreatedById(createdById);
    }

    @GetMapping("/{roomId}")
    public List<ExamDTO> getExamsByRoomId(@PathVariable Long roomId) {
        return examService.getExamsByRoomId(roomId);
    }

    @PostMapping("/{examId}/submit")
    public ResponseEntity<ExamResultDTO> submitExam(@PathVariable Long examId, @RequestBody List<StudentAnswerDTO> answers) {
        ExamResultDTO result = examService.evaluateExam(examId, answers);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
    }
}

