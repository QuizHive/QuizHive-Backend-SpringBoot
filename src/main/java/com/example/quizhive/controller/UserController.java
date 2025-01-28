
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
