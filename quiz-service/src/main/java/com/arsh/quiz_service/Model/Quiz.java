package com.arsh.quiz_service.Model;

import jakarta.persistence.*;

import java.util.List;



@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ElementCollection
    private List<Integer> questionIds;

    public Quiz(){

    }

    public Quiz(List<Integer> questions, String title, Integer id) {
        this.questionIds = questions;
        this.title = title;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getQuestionids() {
        return questionIds;
    }

    public void setQuestionIds(List<Integer> questions) {
        this.questionIds = questions;
    }
}
