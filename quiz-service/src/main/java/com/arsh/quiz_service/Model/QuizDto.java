package com.arsh.quiz_service.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class QuizDto {

    private String category;
    private Integer numQuestions;
    private String title;

    public QuizDto(){

    }

    public QuizDto(String category, Integer numQuestions, String title) {
        this.category = category;
        this.numQuestions = numQuestions;
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(Integer numQuestions) {
        this.numQuestions = numQuestions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
