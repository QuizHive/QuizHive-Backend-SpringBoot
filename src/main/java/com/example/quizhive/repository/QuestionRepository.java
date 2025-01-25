//package com.example.quizhive.repository;
//
//import com.example.quizhive.model.Question;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface QuestionRepository extends MongoRepository<Question, String> {
//    List<Question> findByCategoryIdAndDifficulty(String categoryId, int difficulty);
//}
package com.example.quizhive.repository;

import com.example.quizhive.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

    // Fetch questions by category and difficulty
    List<Question> findByCategoryIdAndDifficulty(String categoryId, String difficulty);

    // Find questions by their title (partial match)
    List<Question> findByTitleContaining(String title);

    // Get all questions created by a specific user
    List<Question> findByCreatedBy(String userId);

    // Count the number of questions in a category
    long countByCategoryId(String categoryId);

    // Delete questions by category ID
    void deleteByCategoryId(String categoryId);
}
