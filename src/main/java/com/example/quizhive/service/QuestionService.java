
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
        questionRepository.deleteByCategory(categoryId);
        categoryRepository.deleteById(categoryId);
    }

    public Question createQuestion(Question question) {
        if (!categoryRepository.existsById(Integer.toString(question.getCategory()))) {
            throw new NotFoundException("Category not found");
        }
        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByCategoryAndDifficulty(Optional<String> categoryId, Optional<String> difficulty, Optional<Integer> limit) {
        return questionRepository.findByCategoryAndDifficulty(
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
        return questionRepository.countByCategory(categoryId);
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
