package com.prototype.ProjectArthur.DTO;

import java.util.Set;

public record RoomDTO(Long id, Integer serie, String curso, Long createdBy, Set<Long> userIds) {
    public RoomDTO(Long id, Integer serie ,String curso, Long createdBy, Set<Long> userIds) {
        this.id = id;
        this.serie = serie;
        this.curso = curso;
        this.createdBy = createdBy != null ? createdBy : -1L; // Default value for null createdById
        this.userIds = userIds;
    }
}

