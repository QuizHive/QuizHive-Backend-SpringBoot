////package com.example.quizhive.model;
////
////import jakarta.persistence.*;
////import java.util.Date;
////import java.util.List;
////
////@Entity
////public class Question {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
////
////    @Column(nullable = false)
////    private String title;
////
////    private String text;
////
////    @ElementCollection
////    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
////    private List<String> options;
////
////    @Column(nullable = false)
////    private int correct;
////
////    @ManyToOne
////    @JoinColumn(name = "category_id", nullable = false)
////    private Category category;
////
////    private int solves;
////
////    @Enumerated(EnumType.ORDINAL)
////    private Difficulty difficulty;
////
////    @Temporal(TemporalType.TIMESTAMP)
////    private Date createdAt;
////
////    // Getters and Setters
////}
//
//package com.example.quizhive.model;
//
//import org.springframework.data.annotation.Id;
//
//import java.util.Date;
//import java.util.List;
//
//@Entity
//public class Question {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String title;
//
//    private String text;
//
//    @ElementCollection
//    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
//    private List<String> options;
//
//    @Column(nullable = false)
//    private int correct;
//
//    @ManyToOne
//    @JoinColumn(name = "category_id", nullable = false)
//    private Category category;
//
//    private int solves;
//
//    @Enumerated(EnumType.ORDINAL)
//    private Difficulty difficulty;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;
//
//    @Transient
//    private Integer lastChoiceByUser; // Non-persistent field
//
//    // Constructors
//    public Question() {}
//
//    public Question(String title, String text, List<String> options, int correct, Category category, int solves, Difficulty difficulty, Date createdAt) {
//        this.title = title;
//        this.text = text;
//        this.options = options;
//        this.correct = correct;
//        this.category = category;
//        this.solves = solves;
//        this.difficulty = difficulty;
//        this.createdAt = createdAt;
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
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public List<String> getOptions() {
//        return options;
//    }
//
//    public void setOptions(List<String> options) {
//        this.options = options;
//    }
//
//    public int getCorrect() {
//        return correct;
//    }
//
//    public void setCorrect(int correct) {
//        this.correct = correct;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public int getSolves() {
//        return solves;
//    }
//
//    public void setSolves(int solves) {
//        this.solves = solves;
//    }
//
//    public Difficulty getDifficulty() {
//        return difficulty;
//    }
//
//    public void setDifficulty(Difficulty difficulty) {
//        this.difficulty = difficulty;
//    }
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Integer getLastChoiceByUser() {
//        return lastChoiceByUser;
//    }
//
//    public void setLastChoiceByUser(Integer lastChoiceByUser) {
//        this.lastChoiceByUser = lastChoiceByUser;
//    }
//}
package com.example.quizhive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Transient;

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

    private String category; // Adjusted to match category name/type from ExpressJS

    private int solves;

    private String difficulty;

    private Date createdAt;

    @Transient
    private Integer lastChoiceByUser; // Non-persistent field for the last choice made by the user

    // Constructors
    public Question() {}

    public Question(String title, String text, List<String> options, int correct, String category, int solves, String difficulty, Date createdAt) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.correct = correct;
        this.category = category;
        this.solves = solves;
        this.difficulty = difficulty;
        this.createdAt = createdAt;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
