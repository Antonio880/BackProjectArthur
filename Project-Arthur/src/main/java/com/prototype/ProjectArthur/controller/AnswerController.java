package com.prototype.ProjectArthur.controller;

import com.prototype.ProjectArthur.DTO.AnswerDTO;
import com.prototype.ProjectArthur.model.Answer;
import com.prototype.ProjectArthur.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping
    public List<AnswerDTO> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping("/{id}")
    public AnswerDTO getAnswerById(@PathVariable Long id) {
        return answerService.getAnswerById(id);
    }

    @PostMapping
    public AnswerDTO createAnswer(@RequestBody Answer answer) {
        return answerService.postAnswer(answer);
    }

    @PutMapping("/{id}")
    public AnswerDTO updateAnswer(@PathVariable Long id, @RequestBody Answer answer) {
        return answerService.updateAnswer(id, answer);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
    }
}

