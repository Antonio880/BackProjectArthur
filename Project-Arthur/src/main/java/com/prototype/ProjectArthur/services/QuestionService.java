package com.prototype.ProjectArthur.services;

import com.prototype.ProjectArthur.DTO.QuestionDTO;
import com.prototype.ProjectArthur.model.Question;
import com.prototype.ProjectArthur.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    public List<QuestionDTO> getAllQuestions() {
        return convertDataToDTOs(repository.findAll());
    }

    public QuestionDTO getQuestionById(Long id) {
        Question question = repository.findById(id).orElse(null);
        if (question != null) {
            return convertData(question);
        } else {
            return null;
        }
    }

    public QuestionDTO postQuestion(Question entity) {
        return convertData(repository.save(entity));
    }

    public QuestionDTO updateQuestion(Long id, Question entity) {
        Question existingQuestion = repository.findById(id).orElse(null);
        if (existingQuestion != null) {
            entity.setId(id);
            return convertData(repository.save(entity));
        } else {
            return null;
        }
    }

    public void deleteQuestion(Long id) {
        repository.deleteById(id);
    }

    private List<QuestionDTO> convertDataToDTOs(List<Question> questions) {
        return questions.stream()
                .map(this::convertData)
                .collect(Collectors.toList());
    }

    private QuestionDTO convertData(Question question) {
        // Implement conversion logic
        return new QuestionDTO(question.getId(), question.getContent(), question.getExam().getId(), question.getCorrectAnswer());
    }
}

