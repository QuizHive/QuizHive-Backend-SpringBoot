package com.example.quizhive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "submits") // Specify the MongoDB collection
public class Submit {

    @Id
    private String id; // MongoDB uses String IDs

    @DBRef
    private Question question; // Reference to the Question model

    @DBRef
    private User user; // Reference to the User model

    private int choice; // User's selected choice

    private boolean isCorrect; // Whether the choice was correct

    private int gainedScore; // Points gained for the submission

    private Date timestamp; // Submission timestamp

    // Constructors
    public Submit() {}

    public Submit(Question question, User user, int choice, boolean isCorrect, int gainedScore, Date timestamp) {
        this.question = question;
        this.user = user;
        this.choice = choice;
        this.isCorrect = isCorrect;
        this.gainedScore = gainedScore;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public int getGainedScore() {
        return gainedScore;
    }

    public void setGainedScore(int gainedScore) {
        this.gainedScore = gainedScore;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
