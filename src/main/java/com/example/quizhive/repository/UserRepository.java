////package com.example.quizhive.repository;
////
////import com.example.quizhive.model.User;
////import org.springframework.data.jpa.repository.JpaRepository;
////import java.util.Optional;
////
//////public interface UserRepository extends JpaRepository<User, Long> {
//////    Optional<User> findByEmail(String email);
//////    boolean existsByEmail(String email);
//////    long countByScoreGreaterThan(int score);
//////}
////@Repository
////public interface UserRepository extends JpaRepository<User, Long> {
////
////    @Query("SELECT u FROM User u ORDER BY u.score DESC")
////    List<User> findTopUsersByScore(Pageable pageable);
////
////    boolean existsByEmail(String email);
////
////    Optional<User> findByEmail(String email);
////}
//
//
//package com.example.quizhive.repository;
//
//import com.example.quizhive.model.User;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface UserRepository extends MongoRepository<User, String> {
//
//    List<User> findTopUsersByScore(Pageable pageable);
//
//    boolean existsByEmail(String email);
//
//    User findByEmail(String email);
//}
package com.example.quizhive.repository;

import com.example.quizhive.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Fetch the top users by score (for leaderboard functionality)
    List<User> findTopUsersByScore(Pageable pageable);

    // Check if a user exists by email
    boolean existsByEmail(String email);

    // Find a user by email
    User findByEmail(String email);

    // Find users followed by a specific user
    List<User> findByFollowersContains(String userId);

    // Find users following a specific user
    List<User> findByFollowingsContains(String userId);

    // Count the number of followers for a user
    long countByFollowersContaining(String userId);

    // Count the number of users a specific user is following
    long countByFollowingsContaining(String userId);
}
