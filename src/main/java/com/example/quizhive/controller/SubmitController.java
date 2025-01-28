package com.example.quizhive.controller;

import com.example.quizhive.model.Submit;
import com.example.quizhive.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/submissions")
public class SubmitController {

    @Autowired
    private SubmitService submitService;

    /**
     * Submit an answer to a question.
     * @param request The submission request payload containing question ID, choice, and user ID.
     * @return The submission result.
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> submitAnswer(@Valid @RequestBody Map<String, Object> request) {
        String userId = (String) request.get("userId");
        String questionId = (String) request.get("questionId");
        int choice = (int) request.get("choice");

        Submit submission = submitService.submitAnswer(userId, questionId, choice);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Answer submitted successfully",
                "data", submission
        ));
    }

    /**
     * Get submissions with optional filters.
     * @param userId Filter by user ID (optional).
     * @param questionId Filter by question ID (optional).
     * @param isCorrect Filter by correctness (optional).
     * @param limit Limit the number of results (optional).
     * @param after Filter submissions after this date (optional).
     * @param before Filter submissions before this date (optional).
     * @return List of submissions matching the filters.
     */
    @GetMapping
    public ResponseEntity<List<Submit>> getSubmissions(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String questionId,
            @RequestParam(required = false) Boolean isCorrect,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Date after,
            @RequestParam(required = false) Date before) {
        List<Submit> submissions = submitService.getSubmissions(
                Optional.ofNullable(userId),
                Optional.ofNullable(questionId),
                Optional.ofNullable(isCorrect),
                Optional.ofNullable(limit),
                Optional.ofNullable(after),
                Optional.ofNullable(before)
        );
        return ResponseEntity.ok(submissions);
    }
}
