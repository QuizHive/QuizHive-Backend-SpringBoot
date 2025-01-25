////package com.example.quizhive.model;
////
////import jakarta.persistence.*;
////import java.util.Date;
////
////@Entity
////public class Submit {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
////
////    @ManyToOne
////    @JoinColumn(name = "question_id", nullable = false)
////    private Question question;
////
////    @ManyToOne
////    @JoinColumn(name = "user_id", nullable = false)
////    private User user;
////
////    @Column(nullable = false)
////    private int choice;
////
////    @Column(nullable = false)
////    private boolean isCorrect;
////
////    @Column(nullable = false)
////    private int gainedScore;
////
////    @Temporal(TemporalType.TIMESTAMP)
////    private Date timestamp;
////
////    // Getters and Setters
////}
//
//package com.example.quizhive.model;
//
//import org.springframework.data.annotation.Id;
//
//import java.util.Date;
//
//@Entity
//public class Submit {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "question_id", nullable = false)
//    private Question question;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @Column(nullable = false)
//    private int choice;
//
//    private boolean correct;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date submittedAt;
//
//    // Constructors
//    public Submit() {}
//
//    public Submit(Question question, User user, int choice, boolean correct, Date submittedAt) {
//        this.question = question;
//        this.user = user;
//        this.choice = choice;
//        this.correct = correct;
//        this.submittedAt = submittedAt;
//    }
//
//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Question getQuestion() {
//        return question;
//    }
//
//    public void setQuestion(Question question) {
//        this.question = question;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public int getChoice() {
//        return choice;
//    }
//
//    public void setChoice(int choice) {
//        this.choice = choice;
//    }
//
//    public boolean isCorrect() {
//        return correct;
//    }
//
//    public void setCorrect(boolean correct) {
//        this.correct = correct;
//    }
//
//    public Date getSubmittedAt() {
//        return submittedAt;
//    }
//
//    public void setSubmittedAt(Date submittedAt) {
//        this.submittedAt = submittedAt;
//    }
//}
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
