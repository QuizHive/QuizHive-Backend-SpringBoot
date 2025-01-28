package com.example.quizhive.controller;

import com.example.quizhive.model.Question;
import com.example.quizhive.model.Category;
import com.example.quizhive.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * Get all categories.
     * @return List of categories.
     */
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = questionService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * Create a new category.
     * @param category The category details.
     * @return The created category.
     */
    @PostMapping("/categories")
    public ResponseEntity<Map<String, Object>> createCategory(@Valid @RequestBody Category category) {
        Category createdCategory = questionService.createCategory(category.getCategoryName(), category.getDescription());
        return ResponseEntity.status(201).body(Map.of(
                "success", true,
                "message", "Category created successfully",
                "data", createdCategory
        ));
    }

    /**
     * Delete a category by its ID.
     * @param categoryId The ID of the category.
     * @return A confirmation message.
     */
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable String categoryId) {
        questionService.deleteCategory(categoryId);
        return ResponseEntity.ok(Map.of("message", "Category deleted successfully"));
    }

    /**
     * Get questions with optional filters.
     * @param categoryId The category ID filter.
     * @param difficulty The difficulty filter.
     * @param limit The number of questions to fetch.
     * @return List of questions matching the filters.
     */
    @GetMapping
    public ResponseEntity<List<Question>> getQuestions(
            @RequestParam Optional<String> categoryId,
            @RequestParam Optional<String> difficulty,
            @RequestParam Optional<Integer> limit) {
        List<Question> questions = questionService.getQuestionsByCategoryAndDifficulty(categoryId, difficulty, limit);
        return ResponseEntity.ok(questions);
    }

    /**
     * Get a specific question by its ID.
     * @param questionId The ID of the question.
     * @return The question details.
     */
    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable String questionId) {
        Question question = questionService.getQuestionById(questionId);
        return ResponseEntity.ok(question);
    }

    /**
     * Create a new question.
     * @param question The question details.
     * @return The created question.
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createQuestion(@Valid @RequestBody Question question) {
        Question createdQuestion = questionService.createQuestion(question);
        return ResponseEntity.status(201).body(Map.of(
                "success", true,
                "message", "Question created successfully",
                "data", createdQuestion
        ));
    }

    /**
     * Delete a question by its ID.
     * @param questionId The ID of the question.
     * @return A confirmation message.
     */
    @DeleteMapping("/{questionId}")
    public ResponseEntity<Map<String, String>> deleteQuestion(@PathVariable String questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok(Map.of("message", "Question deleted successfully"));
    }
}
