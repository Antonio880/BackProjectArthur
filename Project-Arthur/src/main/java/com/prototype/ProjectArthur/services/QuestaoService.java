package com.prototype.ProjectArthur.services;

import com.prototype.ProjectArthur.DTO.QuestionDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuestaoService {

    @Value("${quizapi.url}")
    private String quizApiUrl;

    @Value("${quizapi.apiKey}")
    private String quizApiKey;

    private final RestTemplate restTemplate;

    public QuestaoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public QuestionDTO[] getQuestions(String category, int limit) {
        String url = String.format("%s?apiKey=%s&category=%s&limit=%d", quizApiUrl, quizApiKey, category, limit);
        return restTemplate.getForObject(url, QuestionDTO[].class);
    }
}

