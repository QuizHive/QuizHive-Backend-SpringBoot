package com.example.quizhive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

@Document(collection = "users") // Specify the MongoDB collection
public class User {

    @Id
    private String id; // MongoDB uses String IDs

    private String email;

    private String passHash; // Corresponds to password hash

    private String nickname;

    private String role; // Role represented as a string

    @DBRef
    private List<User> followers; // List of followers

    @DBRef
    private List<User> followings; // List of followings

    private int score; // For leaderboard functionality

    private Date createdAt;

    // Constructors
    public User() {}

    public User(String email, String passHash, String nickname, String role, List<User> followers,
                List<User> followings, int score, Date createdAt) {
        this.email = email;
        this.passHash = passHash;
        this.nickname = nickname;
        this.role = role;
        this.followers = followers;
        this.followings = followings;
        this.score = score;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowings() {
        return followings;
    }

    public void setFollowings(List<User> followings) {
        this.followings = followings;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
