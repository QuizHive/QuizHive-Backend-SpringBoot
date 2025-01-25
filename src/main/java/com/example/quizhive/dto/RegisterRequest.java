//////package com.example.quizhive.dto;
//////
//////public class RegisterRequest {
//////    private String email;
//////    private String passwordHash;
//////    private String nickname;
//////    private String role;
//////
//////    // Getters and Setters
//////    public String getEmail() {
//////        return email;
//////    }
//////
//////    public void setEmail(String email) {
//////        this.email = email;
//////    }
//////
//////    public String getPasswordHash() {
//////        return passwordHash;
//////    }
//////
//////    public void setPasswordHash(String passwordHash) {
//////        this.passwordHash = passwordHash;
//////    }
//////
//////    public String getNickname() {
//////        return nickname;
//////    }
//////
//////    public void setNickname(String nickname) {
//////        this.nickname = nickname;
//////    }
//////
//////    public String getRole() {
//////        return role;
//////    }
//////
//////    public void setRole(String role) {
//////        this.role = role;
//////    }
//////}
////
////package com.example.quizhive.dto;
////
////public class RegisterRequest {
////    private String email;
////    private String password;
////    private String name;
////
////    // Getters and Setters
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
////    public String getName() {
////        return name;
////    }
////
////    public void setName(String name) {
////        this.name = name;
////    }
////}
//package com.example.quizhive.dto;
//
//
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//
//import javax.validation.constraints.Size;
//
//public class RegisterRequest {
//
//    @Email(message = "Invalid email format")
//    @NotBlank(message = "Email is required")
//    private String email;
//
//    @NotBlank(message = "Password is required")
//    @Size(min = 6, message = "Password must be at least 6 characters long")
//    private String password;
//
//    @NotBlank(message = "Name is required")
//    private String name;
//
//    // Getters and Setters
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
package com.example.quizhive.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String passwordHash;

    @NotBlank
    private String nickname;

    @NotBlank
    private String role;

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
}
