//////package com.example.quizhive.model;
//////
//////import jakarta.persistence.*;
//////import java.util.ArrayList;
//////import java.util.Date;
//////import java.util.List;
//////
//////@Entity
//////public class User {
//////
//////    @Id
//////    @GeneratedValue(strategy = GenerationType.IDENTITY)
//////    private Long id;
//////
//////    @Column(nullable = false, unique = true)
//////    private String email;
//////
//////    @Column(nullable = false)
//////    private String passHash;
//////
//////    private String nickname;
//////
//////    @Enumerated(EnumType.STRING)
//////    private Role role;
//////
//////    @ManyToMany
//////    @JoinTable(
//////            name = "user_followers",
//////            joinColumns = @JoinColumn(name = "user_id"),
//////            inverseJoinColumns = @JoinColumn(name = "follower_id")
//////    )
//////    private List<User> followers = new ArrayList<>();
//////
//////    @ManyToMany
//////    @JoinTable(
//////            name = "user_followings",
//////            joinColumns = @JoinColumn(name = "user_id"),
//////            inverseJoinColumns = @JoinColumn(name = "following_id")
//////    )
//////    private List<User> followings = new ArrayList<>();
//////
//////    private int score;
//////
//////    @Temporal(TemporalType.TIMESTAMP)
//////    private Date createdAt;
//////
//////    // Getters and Setters
//////
//////    public IUserInfo getUserInfo() {
//////        return new IUserInfo(id, email, nickname, role, score, createdAt);
//////    }
//////}
////package com.example.quizhive.model;
////
////import jakarta.persistence.*;
////import java.util.Date;
////import java.util.List;
////
////@Entity
////public class User {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
////
////    @Column(nullable = false)
////    private String name;
////
////    @Column(nullable = false, unique = true)
////    private String email;
////
////    @Column(nullable = false)
////    private String password;
////
////    @ElementCollection(fetch = FetchType.EAGER)
////    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
////    private List<String> roles;
////
////    @Temporal(TemporalType.TIMESTAMP)
////    private Date createdAt;
////
////    // Constructors
////    public User() {}
////
////    public User(String name, String email, String password, List<String> roles, Date createdAt) {
////        this.name = name;
////        this.email = email;
////        this.password = password;
////        this.roles = roles;
////        this.createdAt = createdAt;
////    }
////
////    // Getters and Setters
////    public Long getId() {
////        return id;
////    }
////
////    public void setId(Long id) {
////        this.id = id;
////    }
////
////    public String getName() {
////        return name;
////    }
////
////    public void setName(String name) {
////        this.name = name;
////    }
////
////    public String getEmail() {
////        return email;
////    }
////
////    public void setEmail(String email) {
////        this.email = email;
////    }
////
////    public String getPassword() {
////        return password;
////    }
////
////    public void setPassword(String password) {
////        this.password = password;
////    }
////
////    public List<String> getRoles() {
////        return roles;
////    }
////
////    public void setRoles(List<String> roles) {
////        this.roles = roles;
////    }
////
////    public Date getCreatedAt() {
////        return createdAt;
////    }
////
////    public void setCreatedAt(Date createdAt) {
////        this.createdAt = createdAt;
////    }
////}
//
//package com.example.quizhive.model;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import java.util.Date;
//import java.util.List;
//
//@Document(collection = "users") // Specify the MongoDB collection
//public class User {
//
//    @Id
//    private String id; // MongoDB uses String IDs
//
//    private String name;
//
//    private String email;
//
//    private String password;
//
//    private List<String> roles;
//
//    private Date createdAt;
//
//    private int score; // Added for leaderboard functionality
//
//    // Constructors
//    public User() {}
//
//    public User(String name, String email, String password, List<String> roles, Date createdAt, int score) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.roles = roles;
//        this.createdAt = createdAt;
//        this.score = score;
//    }
//
//    // Getters and Setters
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public List<String> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<String> roles) {
//        this.roles = roles;
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
//    public int getScore() {
//        return score;
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }
//}
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
