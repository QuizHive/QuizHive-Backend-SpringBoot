////package com.example.quizhive.service;
////
////import com.example.quizhive.exception.NotFoundException;
////import com.example.quizhive.model.Question;
////import com.example.quizhive.model.Submit;
////import com.example.quizhive.model.User;
////import com.example.quizhive.repository.QuestionRepository;
////import com.example.quizhive.repository.SubmitRepository;
////import com.example.quizhive.repository.UserRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import java.util.Date;
////import java.util.List;
////
////@Service
////public class SubmitService {
////
////    @Autowired
////    private UserRepository userRepository;
////
////    @Autowired
////    private QuestionRepository questionRepository;
////
////    @Autowired
////    private SubmitRepository submitRepository;
////
////    public Submit submit(String userId, String questionId, int choice) {
////        User user = userRepository.findById(userId)
////                .orElseThrow(() -> new NotFoundException("User not found"));
////        Question question = questionRepository.findById(questionId)
////                .orElseThrow(() -> new NotFoundException("Question not found"));
////
////        boolean isCorrect = question.getCorrectAnswer() == choice;
////        int gainedScore = isCorrect ? question.getDifficulty() : 0;
////
////        Submit submit = new Submit(user, question, choice, isCorrect, gainedScore, new Date());
////        user.setScore(user.getScore() + gainedScore);
////        userRepository.save(user);
////        return submitRepository.save(submit);
////    }
////
////    public List<Submit> getSubmissions(String userId, String questionId) {
////        return submitRepository.findByUserIdAndQuestionId(userId, questionId);
////    }
////}
//
//package com.example.quizhive.service;
//
//import com.example.quizhive.exception.NotFoundException;
//import com.example.quizhive.model.Question;
//import com.example.quizhive.model.Submit;
//import com.example.quizhive.model.User;
//import com.example.quizhive.repository.QuestionRepository;
//import com.example.quizhive.repository.SubmitRepository;
//import com.example.quizhive.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class SubmitService {
//
//    @Autowired
//    private SubmitRepository submitRepository;
//
//    @Autowired
//    private QuestionRepository questionRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public Submit submitAnswer(Long questionId, Long userId, int choice) {
//        Question question = questionRepository.findById(questionId)
//                .orElseThrow(() -> new NotFoundException("Question not found"));
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException("User not found"));
//
//        Submit submit = new Submit();
//        submit.setQuestion(question);
//        submit.setUser(user);
//        submit.setChoice(choice);
//        submit.setCorrect(choice == question.getCorrect()); // Logic to check correctness
//        submit.setSubmittedAt(new Date());
//        return submitRepository.save(submit);
//    }
//}
package com.example.quizhive.service;

import com.example.quizhive.exception.NotFoundException;
import com.example.quizhive.model.Question;
import com.example.quizhive.model.Submit;
import com.example.quizhive.model.User;
import com.example.quizhive.repository.QuestionRepository;
import com.example.quizhive.repository.SubmitRepository;
import com.example.quizhive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubmitService {

    @Autowired
    private SubmitRepository submitRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Map<String, Integer> DIFFICULTY_MAP = Map.of(
            "EASY", 1,
            "MEDIUM", 2,
            "HARD", 3
    );

    /**
     * Submit an answer to a question.
     * @param userId The ID of the user making the submission.
     * @param questionId The ID of the question being answered.
     * @param choice The user's chosen answer.
     * @return The saved submission record.
     */
    public Submit submitAnswer(String userId, String questionId, int choice) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        boolean isCorrect = question.getCorrect() == choice;
        int gainedScore = isCorrect ? DIFFICULTY_MAP.getOrDefault(question.getDifficulty().toUpperCase(), 0) : 0;

        Submit submit = new Submit();
        submit.setQuestion(question);
        submit.setUser(user);
        submit.setChoice(choice);
        submit.setCorrect(isCorrect);
        submit.setGainedScore(gainedScore);
        submit.setTimestamp(new Date());

        // Update user score
        user.setScore(user.getScore() + gainedScore);
        userRepository.save(user);

        // Update question solve count if correct
        if (isCorrect) {
            question.setSolves(question.getSolves() + 1);
            questionRepository.save(question);
        }

        return submitRepository.save(submit);
    }

    /**
     * Get all submissions with optional filters.
     *
     * @param userId     Filter by user ID (optional).
     * @param questionId Filter by question ID (optional).
     * @param isCorrect  Filter by correctness of the answer (optional).
     * @param limit      Limit the number of results (optional).
     * @param after      Filter by submissions after this date (optional).
     * @param before     Filter by submissions before this date (optional).
     * @return A list of submissions matching the filters.
     */
    public List<Submit> getSubmissions(Optional<String> userId, Optional<String> questionId,
                                       Optional<Boolean> isCorrect, Optional<Integer> limit,
                                       Optional<Date> after, Optional<Date> before) {
        return submitRepository.findAll().stream()
                .filter(submit -> userId.map(id -> id.equals(submit.getUser().getId())).orElse(true))
                .filter(submit -> questionId.map(id -> id.equals(submit.getQuestion().getId())).orElse(true))
                .filter(submit -> isCorrect.map(correct -> correct == submit.isCorrect()).orElse(true))
                .filter(submit -> after.map(date -> !submit.getTimestamp().before(date)).orElse(true))
                .filter(submit -> before.map(date -> !submit.getTimestamp().after(date)).orElse(true))
                .limit(limit.orElse(10))
                .collect(Collectors.toList());
    }

    /**
     * Get all submissions for a specific question.
     * @param questionId The ID of the question.
     * @return A list of submissions for the question.
     */
    public List<Submit> getSubmissionsByQuestion(String questionId) {
        return submitRepository.findByQuestionId(questionId);
    }

    /**
     * Get all submissions by a specific user.
     * @param userId The ID of the user.
     * @return A list of submissions made by the user.
     */
    public List<Submit> getSubmissionsByUser(String userId) {
        return submitRepository.findByUserId(userId);
    }
}
