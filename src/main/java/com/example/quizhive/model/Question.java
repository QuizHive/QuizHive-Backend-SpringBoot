package com.example.quizhive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Transient;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Document(collection = "questions")
public class Question {

    @Id
    private String id;

    private String title;

    private String text;

    private List<String> options;

    private int correct;

    private int category;

    private int solves;

    private List<Integer> createdBy;

    private String difficulty;

    private Date createdAt;

    @Transient
    private Integer lastChoiceByUser; // Non-persistent field for the last choice made by the user

    // Constructors
    public Question() {}

    public Question(String title, String text, List<String> options, int correct, int category, int solves, String difficulty, Date createdAt, List<Integer> createdBy) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.correct = correct;
        this.category = category;
        this.solves = solves;
        this.difficulty = difficulty;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public void setCreatedBy(List<Integer> createdBy) {
        this.createdBy = createdBy;
    }

    public List<Integer> getCreatedBy() {
        return createdBy;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSolves() {
        return solves;
    }

    public void setSolves(int solves) {
        this.solves = solves;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getLastChoiceByUser() {
        return lastChoiceByUser;
    }

    public void setLastChoiceByUser(Integer lastChoiceByUser) {
        this.lastChoiceByUser = lastChoiceByUser;
    }
}
