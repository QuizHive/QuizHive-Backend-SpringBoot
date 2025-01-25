//package com.example.quizhive.repository;
//
//import com.example.quizhive.model.Category;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface CategoryRepository extends MongoRepository<Category, String> {
//    boolean existsByCategoryName(String categoryName);
//}
package com.example.quizhive.repository;

import com.example.quizhive.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    // Check if a category exists by its name
    boolean existsByCategoryName(String categoryName);

    // Find a category by its name
    Category findByCategoryName(String categoryName);

    // Count all categories
    long count();

    // Delete a category by its name
    void deleteByCategoryName(String categoryName);
}
