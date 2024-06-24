package com.prototype.ProjectArthur.services;

import com.prototype.ProjectArthur.DTO.ExamDTO;
import com.prototype.ProjectArthur.DTO.ExamResultDTO;
import com.prototype.ProjectArthur.DTO.QuestionDTO;
import com.prototype.ProjectArthur.DTO.StudentAnswerDTO;
import com.prototype.ProjectArthur.model.Exam;
import com.prototype.ProjectArthur.model.ExamQuestion;
import com.prototype.ProjectArthur.model.Room;
import com.prototype.ProjectArthur.model.User;
import com.prototype.ProjectArthur.repository.ExamQuestionRepository;
import com.prototype.ProjectArthur.repository.ExamRepository;
import com.prototype.ProjectArthur.repository.RoomRepository;
import com.prototype.ProjectArthur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.prototype.ProjectArthur.config.corsConfiguration.RestTemplateConfig;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    public ExamDTO createExam(String category, int questionCount, Long roomId, Long createdById) {
        Exam exam = new Exam();
        exam.setCategory(category);
        exam.setQuestionCount(questionCount);

        Optional<Room> room = roomRepository.findById(roomId);
        room.ifPresent(exam::setRoom);

        Optional<User> createdBy = userRepository.findById(createdById);
        createdBy.ifPresent(exam::setCreatedBy);

        Exam savedExam = examRepository.save(exam);
        return convertToDTO(savedExam);
    }

    private ExamDTO convertToDTO(Exam exam) {
        return new ExamDTO(exam.getId(), exam.getCategory(), exam.getQuestionCount(), exam.getRoom() != null ? exam.getRoom().getId() : null, exam.getCreatedBy() != null ? exam.getCreatedBy().getId() : null, exam.getCreatedAt());
    }

    public QuestionDTO[] generateQuestions(String category, int limit) {
        String url = "https://quizapi.io/api/v1/questions?apiKey=ioBFeFdiBWVE0oOGl2bjQhtCb6QYthKGzC2dBgtk&category=" + category + "&limit=" + limit;
        ResponseEntity<QuestionDTO[]> response = restTemplate.getForEntity(url, QuestionDTO[].class);
        QuestionDTO[] questions = response.getBody();
        return questions;
    }

    public void saveGeneratedQuestions(Long examId, QuestionDTO[] questions) {
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new RuntimeException("Exam not found"));
        List<ExamQuestion> examQuestions = Arrays.stream(questions).map(q -> {
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setExam(exam);
            examQuestion.setQuestion(q.getQuestion());
            examQuestion.setDescription(q.getDescription());
            examQuestion.setAnswers(q.getAnswers());

            Map<String, Boolean> correctAnswers = q.getCorrect_answers().entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> Boolean.parseBoolean(entry.getValue())
                    ));
            examQuestion.setCorrectAnswers(correctAnswers);

            examQuestion.setMultipleCorrectAnswers(q.getMultiple_correct_answers());
            examQuestion.setCorrectAnswer(q.getCorrect_answer());
            examQuestion.setExplanation(q.getExplanation());
            examQuestion.setTip(q.getTip());
            examQuestion.setCategory(q.getCategory());
            examQuestion.setDifficulty(q.getDifficulty());
            return examQuestion;
        }).collect(Collectors.toList());

        // Salva as questões e atualiza o exame
        examQuestions = examQuestionRepository.saveAll(examQuestions);
        exam.setQuestions(examQuestions);
        examRepository.save(exam);

        // Log dos IDs das questões salvas
        examQuestions.forEach(q -> System.out.println("Pergunta gerada ID: " + q.getId()));
    }

    public List<ExamDTO> getExamsByCreatedById(Long createdById) {
        List<Exam> exams = examRepository.findByCreatedById(createdById);
        return exams.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ExamDTO> getExamsByRoomId(Long roomId) {
        List<Exam> exams = examRepository.findByRoomId(roomId);
        return exams.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteExam(Long id) {
        Exam exam = examRepository.findById(id).orElse(null);
        if (exam != null) {
            examRepository.delete(exam);
        }
    }

    public ExamResultDTO evaluateExam(Long examId, List<StudentAnswerDTO> answers) {
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new RuntimeException("Exam not found"));
        List<ExamQuestion> questions = examQuestionRepository.findByExamId(examId);

        int correctAnswers = 0;
        int incorrectAnswers = 0;

        // Log para verificar os IDs das perguntas
        System.out.println("Perguntas do exame:");
        questions.forEach(q -> System.out.println("Question ID: " + q.getId()));

        for (StudentAnswerDTO answer : answers) {
            System.out.println("Resposta recebida para a pergunta ID: " + answer.questionId());

            ExamQuestion question = questions.stream()
                    .filter(q -> q.getId().equals(answer.questionId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            String correctAnswerKey = question.getCorrectAnswers().entrySet().stream()
                    .filter(Map.Entry::getValue)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);

            if (correctAnswerKey != null && correctAnswerKey.equals(answer.selectedAnswer())) {
                correctAnswers++;
            } else {
                incorrectAnswers++;
            }
        }

        return new ExamResultDTO(questions.size(), correctAnswers, incorrectAnswers);
    }

}