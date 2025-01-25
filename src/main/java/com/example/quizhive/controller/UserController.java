//////package com.example.quizhive.controller;
//////
//////import com.example.quizhive.service.UserService;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.http.ResponseEntity;
//////import org.springframework.web.bind.annotation.*;
//////
//////@RestController
//////@RequestMapping("/users")
//////public class UserController {
//////
//////    @Autowired
//////    private UserService userService;
//////
//////    @GetMapping("/me")
//////    public ResponseEntity<?> getCurrentUserInfo(@RequestAttribute("user") User user) {
//////        return ResponseEntity.ok(userService.getUserInfo(user));
//////    }
//////
//////    @GetMapping("/info/{userId}")
//////    public ResponseEntity<?> getUserById(@PathVariable String userId) {
//////        return ResponseEntity.ok(userService.getUserInfoById(userId));
//////    }
//////
//////    @GetMapping("/leaderboard")
//////    public ResponseEntity<?> getLeaderboard(@RequestParam(value = "limit", defaultValue = "10") int limit) {
//////        return ResponseEntity.ok(userService.getLeaderboard(limit));
//////    }
//////}
////
////package com.example.quizhive.controller;
////
////import com.example.quizhive.model.User;
////import com.example.quizhive.service.UserService;
////import io.swagger.v3.oas.annotations.Operation;
////import io.swagger.v3.oas.annotations.responses.ApiResponse;
////import io.swagger.v3.oas.annotations.responses.ApiResponses;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////
////@RestController
////@RequestMapping("/users")
////public class UserController {
////
////    @Autowired
////    private UserService userService;
////
////    @Operation(summary = "Get current user info", description = "Retrieve information of the currently authenticated user.")
////    @ApiResponses(value = {
////            @ApiResponse(responseCode = "200", description = "Successfully retrieved user info"),
////            @ApiResponse(responseCode = "401", description = "Unauthorized access")
////    })
////    @GetMapping("/me")
////    public ResponseEntity<?> getCurrentUserInfo(@RequestAttribute("user") User user) {
////        return ResponseEntity.ok(userService.getUserInfo(user));
////    }
////
////    @Operation(summary = "Get user info by ID", description = "Retrieve user info by user ID.")
////    @ApiResponses(value = {
////            @ApiResponse(responseCode = "200", description = "Successfully retrieved user info"),
////            @ApiResponse(responseCode = "404", description = "User not found")
////    })
////    @GetMapping("/info/{userId}")
////    public ResponseEntity<?> getUserById(@PathVariable String userId) {
////        return ResponseEntity.ok(userService.getUserInfoById(userId));
////    }
////
////    @Operation(summary = "Get leaderboard", description = "Retrieve the leaderboard with top users sorted by score.")
////    @ApiResponses(value = {
////            @ApiResponse(responseCode = "200", description = "Leaderboard retrieved successfully"),
////            @ApiResponse(responseCode = "500", description = "Internal server error")
////    })
////    @GetMapping("/leaderboard")
////    public ResponseEntity<?> getLeaderboard(@RequestParam(value = "limit", defaultValue = "10") int limit) {
////        return ResponseEntity.ok(userService.getLeaderboard(limit));
////    }
////}
//package com.example.quizhive.controller;
//
//import com.example.quizhive.model.User;
//import com.example.quizhive.service.UserService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @Operation(summary = "Get current user info", description = "Retrieve information of the currently authenticated user.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully retrieved user info"),
//            @ApiResponse(responseCode = "401", description = "Unauthorized access")
//    })
//    @GetMapping("/me")
//    public ResponseEntity<?> getCurrentUserInfo(@RequestAttribute("user") User user) {
//        return ResponseEntity.ok(userService.getUserInfo(user));
//    }
//
//    @Operation(summary = "Get user info by ID", description = "Retrieve user info by user ID.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully retrieved user info"),
//            @ApiResponse(responseCode = "404", description = "User not found")
//    })
//    @GetMapping("/info/{userId}")
//    public ResponseEntity<?> getUserById(@PathVariable String userId) {
//        return ResponseEntity.ok(userService.getUserInfoById(userId));
//    }
//
//    @Operation(summary = "Get leaderboard", description = "Retrieve the leaderboard with top users sorted by score.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Leaderboard retrieved successfully"),
//            @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    @GetMapping("/leaderboard")
//    public ResponseEntity<?> getLeaderboard(@RequestParam(value = "limit", defaultValue = "10") int limit) {
//        return ResponseEntity.ok(userService.getLeaderboard(limit));
//    }
//}

package com.example.quizhive.controller;

import com.example.quizhive.model.User;
import com.example.quizhive.service.UserService;
import com.example.quizhive.service.UserService.ScoreboardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get information of the currently authenticated user.
     * @param userId The ID of the authenticated user.
     * @return The user details.
     */
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUserInfo(@RequestHeader("userId") String userId) {
        User user = userService.getUserInfo(userId);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "User info retrieved successfully",
                "data", user
        ));
    }

    /**
     * Get user information by user ID.
     * @param userId The ID of the user.
     * @return The user details.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable String userId) {
        User user = userService.getUserInfo(userId);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "User info retrieved successfully",
                "data", user
        ));
    }

    /**
     * Get the leaderboard with top users sorted by score.
     * @param userId The ID of the user requesting the leaderboard.
     * @param limit The number of top users to retrieve.
     * @return The leaderboard data including the user's rank.
     */
    @GetMapping("/leaderboard")
    public ResponseEntity<Map<String, Object>> getLeaderboard(
            @RequestHeader("userId") String userId,
            @RequestParam(defaultValue = "10") int limit) {
        ScoreboardResponse scoreboard = userService.getScoreboard(userId, limit);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Leaderboard retrieved successfully",
                "data", Map.of(
                        "scoreboard", scoreboard.getScoreboard(),
                        "userRank", scoreboard.getUserRank()
                )
        ));
    }
}
