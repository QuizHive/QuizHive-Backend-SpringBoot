package com.example.quizhive.dto;

import jakarta.validation.constraints.NotBlank;

import javax.validation.constraints.*;
import java.util.List;

public class CreateQuestionRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Text is required")
    private String text;

    @NotNull(message = "Options are required")
    private List<String> options;

    @NotNull(message = "Correct option index is required")
    private Integer correct;

    @NotBlank(message = "Category ID is required")
    private String categoryId;

    @NotNull(message = "Difficulty level is required")
    private Integer difficulty;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }
}