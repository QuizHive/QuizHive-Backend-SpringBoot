////package com.example.quizhive.service;
////
////import com.example.quizhive.exception.ConflictException;
////import com.example.quizhive.exception.NotFoundException;
////import com.example.quizhive.model.Category;
////import com.example.quizhive.model.Question;
////import com.example.quizhive.repository.CategoryRepository;
////import com.example.quizhive.repository.QuestionRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import java.util.List;
////
////@Service
////public class QuestionService {
////
////    @Autowired
////    private CategoryRepository categoryRepository;
////
////    @Autowired
////    private QuestionRepository questionRepository;
////
////    public List<Category> getAllCategories() {
////        return categoryRepository.findAll();
////    }
////
////    public Category createCategory(String categoryName, String description) {
////        if (categoryRepository.existsByCategoryName(categoryName)) {
////            throw new ConflictException("Category already exists");
////        }
////        Category category = new Category(categoryName, description);
////        return categoryRepository.save(category);
////    }
////
////    public void deleteCategory(String categoryId) {
////        if (!categoryRepository.existsById(categoryId)) {
////            throw new NotFoundException("Category not found");
////        }
////        questionRepository.deleteByCategoryId(categoryId);
////        categoryRepository.deleteById(categoryId);
////    }
////
////    public Question createQuestion(Question question) {
////        if (!categoryRepository.existsById(question.getCategoryId())) {
////            throw new NotFoundException("Category not found");
////        }
////        return questionRepository.save(question);
////    }
////
////    public List<Question> getQuestions(String categoryId, Integer difficulty, Integer limit) {
////        return questionRepository.findByCategoryIdAndDifficulty(categoryId, difficulty, limit);
////    }
////
////    public Question getQuestionById(String questionId) {
////        return questionRepository.findById(questionId)
////                .orElseThrow(() -> new NotFoundException("Question not found"));
////    }
////
////    public void deleteQuestion(String questionId) {
////        if (!questionRepository.existsById(questionId)) {
////            throw new NotFoundException("Question not found");
////        }
////        questionRepository.deleteById(questionId);
////    }
////}
//
//package com.example.quizhive.service;
//
//import com.example.quizhive.exception.ConflictException;
//import com.example.quizhive.exception.NotFoundException;
//import com.example.quizhive.model.Category;
//import com.example.quizhive.model.Question;
//import com.example.quizhive.repository.CategoryRepository;
//import com.example.quizhive.repository.QuestionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class QuestionService {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    private QuestionRepository questionRepository;
//
//    public List<Category> getAllCategories() {
//        return categoryRepository.findAll();
//    }
//
//    public Category createCategory(String categoryName, String description) {
//        if (categoryRepository.existsByCategoryName(categoryName)) {
//            throw new ConflictException("Category already exists");
//        }
//        Category category = new Category(categoryName, description);
//        return categoryRepository.save(category);
//    }
//
//    public void deleteCategory(String categoryId) {
//        if (!categoryRepository.existsById(categoryId)) {
//            throw new NotFoundException("Category not found");
//        }
//        questionRepository.deleteByCategoryId(categoryId);
//        categoryRepository.deleteById(categoryId);
//    }
//
//    public Question createQuestion(Question question) {
//        if (!categoryRepository.existsById(question.getCategory().getId())) {
//            throw new NotFoundException("Category not found");
//        }
//        return questionRepository.save(question);
//    }
//
//    public List<Question> getQuestions(Optional<String> categoryId, Optional<Integer> difficulty, Optional<Integer> limit) {
//        if (limit.isPresent()) {
//            return questionRepository.findByCategoryAndDifficultyWithLimit(
//                    categoryId.orElse(null), difficulty.orElse(null), limit.get());
//        } else {
//            return questionRepository.findByCategoryAndDifficulty(categoryId.orElse(null), difficulty.orElse(null));
//        }
//    }
//
//    public Question getQuestionById(Long questionId) {
//        return questionRepository.findById(questionId)
//                .orElseThrow(() -> new NotFoundException("Question not found"));
//    }
//
//    public void deleteQuestion(Long questionId) {
//        if (!questionRepository.existsById(questionId)) {
//            throw new NotFoundException("Question not found");
//        }
//        questionRepository.deleteById(questionId);
//    }
//}

package com.example.quizhive.service;

import com.example.quizhive.exception.ConflictException;
import com.example.quizhive.exception.NotFoundException;
import com.example.quizhive.model.Category;
import com.example.quizhive.model.Question;
import com.example.quizhive.repository.CategoryRepository;
import com.example.quizhive.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(String categoryName, String description) {
        if (categoryRepository.existsByCategoryName(categoryName)) {
            throw new ConflictException("Category already exists");
        }
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setDescription(description);
        return categoryRepository.save(category);
    }

    public void deleteCategory(String categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException("Category not found");
        }
        questionRepository.deleteByCategoryId(categoryId);
        categoryRepository.deleteById(categoryId);
    }

    public Question createQuestion(Question question) {
        if (!categoryRepository.existsById(question.getCategory())) {
            throw new NotFoundException("Category not found");
        }
        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByCategoryAndDifficulty(Optional<String> categoryId, Optional<String> difficulty, Optional<Integer> limit) {
        return questionRepository.findByCategoryIdAndDifficulty(
                categoryId.orElse(null),
                difficulty.orElse(null)
        );
    }

    public List<Question> getQuestionsByTitle(String title) {
        return questionRepository.findByTitleContaining(title);
    }

    public List<Question> getQuestionsByUser(String userId) {
        return questionRepository.findByCreatedBy(userId);
    }

    public long countQuestionsInCategory(String categoryId) {
        return questionRepository.countByCategoryId(categoryId);
    }

    public Question getQuestionById(String questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question not found"));
    }

    public void deleteQuestion(String questionId) {
        if (!questionRepository.existsById(questionId)) {
            throw new NotFoundException("Question not found");
        }
        questionRepository.deleteById(questionId);
    }
}
