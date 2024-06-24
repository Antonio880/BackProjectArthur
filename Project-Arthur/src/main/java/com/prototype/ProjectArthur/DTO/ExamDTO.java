package com.prototype.ProjectArthur.DTO;

import java.time.LocalDateTime;

public class ExamDTO {
    private Long id;
    private String category;
    private int questionCount;
    private Long roomId;
    private Long createdById;
    private LocalDateTime createdAt;

    public ExamDTO(Long id, String category, int questionCount, Long roomId, Long createdById, LocalDateTime createdAt) {
        this.id = id;
        this.category = category;
        this.questionCount = questionCount;
        this.roomId = roomId;
        this.createdById = createdById;
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }
}