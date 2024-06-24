package com.prototype.ProjectArthur.model;

import java.util.Set;
import jakarta.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer serie;
    private String curso;
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @OneToMany(mappedBy = "room")
    private Set<User> users;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private Set<Exam> exams;

    public Room() {}

    public Room(Integer serie, String curso, User createdBy) {
        this.serie = serie;
        this.curso = curso;
        this.createdBy = createdBy;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Set<User> getStudents() {
        return users;
    }

    public void setStudents(Set<User> students) {
        this.users = students;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }
}
