//package com.example.quizhive.repository;
//
//import com.example.quizhive.model.Submit;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface SubmitRepository extends MongoRepository<Submit, String> {
//    List<Submit> findByUserIdAndQuestionId(String userId, String questionId);
//}
package com.example.quizhive.repository;

import com.example.quizhive.model.Submit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitRepository extends MongoRepository<Submit, String> {

    // Fetch submissions by user and question
    List<Submit> findByUserIdAndQuestionId(String userId, String questionId);

    // Get all submissions for a specific question
    List<Submit> findByQuestionId(String questionId);

    // Get all submissions by a specific user
    List<Submit> findByUserId(String userId);

    // Count submissions for a question
    long countByQuestionId(String questionId);

    // Count correct submissions by a user for a specific question
    long countByUserIdAndQuestionIdAndIsCorrect(String userId, String questionId, boolean isCorrect);
}
