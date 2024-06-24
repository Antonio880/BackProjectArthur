package com.prototype.ProjectArthur.model;

import jakarta.persistence.*;

import java.util.Map;

@Entity
public class ExamQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    private String question;
    private String description;

    @ElementCollection
    @CollectionTable(name = "exam_question_answers", joinColumns = @JoinColumn(name = "question_id"))
    @MapKeyColumn(name = "answer_key")
    @Column(name = "answer_value")
    private Map<String, String> answers;

    @ElementCollection
    @CollectionTable(name = "exam_question_correct_answers", joinColumns = @JoinColumn(name = "question_id"))
    @MapKeyColumn(name = "answer_key")
    @Column(name = "is_correct")
    private Map<String, Boolean> correctAnswers;

    private String multipleCorrectAnswers;
    private String correctAnswer;
    private String explanation;
    private String tip;
    private String category;
    private String difficulty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }

    public Map<String, Boolean> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Map<String, Boolean> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public String getMultipleCorrectAnswers() {
        return multipleCorrectAnswers;
    }

    public void setMultipleCorrectAnswers(String multipleCorrectAnswers) {
        this.multipleCorrectAnswers = multipleCorrectAnswers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
