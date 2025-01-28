package com.example.quizhive.repository;

import com.example.quizhive.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

    // Fetch questions by category and difficulty
    List<Question> findByCategoryAndDifficulty(String categoryId, String difficulty);

    // Find questions by their title (partial match)
    List<Question> findByTitleContaining(String title);

    // Get all questions created by a specific user
    List<Question> findByCreatedBy(String userId);

    // Count the number of questions in a category
    long countByCategory(String categoryId);

    // Delete questions by category ID
    void deleteByCategory(String categoryId);
}
