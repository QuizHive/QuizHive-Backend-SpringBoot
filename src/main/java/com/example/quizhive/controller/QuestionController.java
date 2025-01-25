////package com.example.quizhive.controller;
////
////import com.example.quizhive.dto.*;
////import com.example.quizhive.service.QuestionService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.Map;
////
////@RestController
////@RequestMapping("/questions")
////public class QuestionController {
////
////    @Autowired
////    private QuestionService questionService;
////
////    @GetMapping("/categories")
////    public ResponseEntity<?> getCategories() {
////        return ResponseEntity.ok(questionService.getAllCategories());
////    }
////
////    @PostMapping("/category")
////    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest request) {
////        return ResponseEntity.status(201).body(questionService.createCategory(request));
////    }
////
////    @DeleteMapping("/categories/{id}")
////    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
////        questionService.deleteCategory(id);
////        return ResponseEntity.ok("Category deleted successfully");
////    }
////
////    @GetMapping
////    public ResponseEntity<?> getQuestions(@RequestParam Map<String, String> filters) {
////        return ResponseEntity.ok(questionService.getQuestions(filters));
////    }
////
////    @GetMapping("/{id}")
////    public ResponseEntity<?> getQuestionById(@PathVariable String id, @RequestAttribute("user") User user) {
////        return ResponseEntity.ok(questionService.getQuestionById(id, user));
////    }
////
////    @PostMapping
////    public ResponseEntity<?> createQuestion(@RequestBody CreateQuestionRequest request) {
////        return ResponseEntity.status(201).body(questionService.createQuestion(request));
////    }
////
////    @DeleteMapping("/{id}")
////    public ResponseEntity<?> deleteQuestion(@PathVariable String id) {
////        questionService.deleteQuestion(id);
////        return ResponseEntity.ok("Question deleted successfully");
////    }
////}
//
//package com.example.quizhive.controller;
//
//import com.example.quizhive.dto.*;
//import com.example.quizhive.service.QuestionService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/questions")
//public class QuestionController {
//
//    @Autowired
//    private QuestionService questionService;
//
//    @Operation(summary = "Get all categories", description = "Retrieve all available question categories.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully")
//    })
//    @GetMapping("/categories")
//    public ResponseEntity<?> getCategories() {
//        return ResponseEntity.ok(questionService.getAllCategories());
//    }
//
//    @Operation(summary = "Create a category", description = "Create a new question category (Admin only).")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Category created successfully"),
//            @ApiResponse(responseCode = "403", description = "Forbidden - Admin access required")
//    })
//    @PostMapping("/category")
//    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest request) {
//        return ResponseEntity.status(201).body(questionService.createCategory(request));
//    }
//
//    @Operation(summary = "Delete a category", description = "Delete an existing question category (Admin only).")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Category deleted successfully"),
//            @ApiResponse(responseCode = "404", description = "Category not found")
//    })
//    @DeleteMapping("/categories/{id}")
//    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
//        questionService.deleteCategory(id);
//        return ResponseEntity.ok("Category deleted successfully");
//    }
//
//    @Operation(summary = "Get all questions", description = "Retrieve all questions with optional filters.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Questions retrieved successfully")
//    })
//    @GetMapping
//    public ResponseEntity<?> getQuestions(@RequestParam Map<String, String> filters) {
//        return ResponseEntity.ok(questionService.getQuestions(filters));
//    }
//
//    @Operation(summary = "Get question by ID", description = "Retrieve details of a question by ID.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Question retrieved successfully"),
//            @ApiResponse(responseCode = "404", description = "Question not found")
//    })
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getQuestionById(@PathVariable String id, @RequestAttribute("user") User user) {
//        return ResponseEntity.ok(questionService.getQuestionById(id, user));
//    }
//
//    @Operation(summary = "Create a question", description = "Create a new question (Admin only).")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Question created successfully"),
//            @ApiResponse(responseCode = "403", description = "Forbidden - Admin access required")
//    })
//    @PostMapping
//    public ResponseEntity<?> createQuestion(@RequestBody CreateQuestionRequest request) {
//        return ResponseEntity.status(201).body(questionService.createQuestion(request));
//    }
//
//    @Operation(summary = "Delete a question", description = "Delete an existing question (Admin only).")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Question deleted successfully"),
//            @ApiResponse(responseCode = "404", description = "Question not found")
//    })
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteQuestion(@PathVariable String id) {
//        questionService.deleteQuestion(id);
//        return ResponseEntity.ok("Question deleted successfully");
//    }
//}
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
