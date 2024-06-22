package com.prototype.ProjectArthur.services;

import com.prototype.ProjectArthur.DTO.AnswerDTO;
import com.prototype.ProjectArthur.model.Answer;
import com.prototype.ProjectArthur.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository repository;

    public List<AnswerDTO> getAllAnswers() {
        return convertDataToDTOs(repository.findAll());
    }

    public AnswerDTO getAnswerById(Long id) {
        Answer answer = repository.findById(id).orElse(null);
        if (answer != null) {
            return convertData(answer);
        } else {
            return null;
        }
    }

    public AnswerDTO postAnswer(Answer entity) {
        return convertData(repository.save(entity));
    }

    public AnswerDTO updateAnswer(Long id, Answer entity) {
        Answer existingAnswer = repository.findById(id).orElse(null);
        if (existingAnswer != null) {
            entity.setId(id);
            return convertData(repository.save(entity));
        } else {
            return null;
        }
    }

    public void deleteAnswer(Long id) {
        repository.deleteById(id);
    }

    private List<AnswerDTO> convertDataToDTOs(List<Answer> answers) {
        return answers.stream()
                .map(this::convertData)
                .collect(Collectors.toList());
    }

    private AnswerDTO convertData(Answer answer) {
        // Implement conversion logic
        return new AnswerDTO(answer.getId(), answer.getContent(), answer.getQuestion().getId());
    }
}

