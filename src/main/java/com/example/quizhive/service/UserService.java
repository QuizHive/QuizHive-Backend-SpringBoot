//////package com.example.quizhive.service;
//////
//////import com.example.quizhive.exception.NotFoundException;
//////import com.example.quizhive.model.User;
//////import com.example.quizhive.repository.UserRepository;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.stereotype.Service;
//////
//////import java.util.List;
//////import java.util.stream.Collectors;
//////
//////@Service
//////public class UserService {
//////
//////    @Autowired
//////    private UserRepository userRepository;
//////
//////    public User getUserInfo(String userId) {
//////        return userRepository.findById(userId)
//////                .orElseThrow(() -> new NotFoundException("User not found"));
//////    }
//////
//////    public List<User> getAllUsers() {
//////        return userRepository.findAll();
//////    }
//////
//////    public List<User> getScoreboard(String userId, int limit) {
//////        List<User> allUsers = userRepository.findAll()
//////                .stream()
//////                .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
//////                .limit(limit)
//////                .collect(Collectors.toList());
//////
//////        User loggedInUser = userRepository.findById(userId)
//////                .orElseThrow(() -> new NotFoundException("User not found"));
//////
//////        if (!allUsers.contains(loggedInUser)) {
//////            int rank = (int) userRepository.countByScoreGreaterThan(loggedInUser.getScore());
//////            loggedInUser.setRank(rank + 1);
//////            allUsers.add(loggedInUser);
//////        }
//////        return allUsers;
//////    }
//////}
////
////package com.example.quizhive.service;
////
////import com.example.quizhive.exception.NotFoundException;
////import com.example.quizhive.model.User;
////import com.example.quizhive.repository.UserRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////@Service
////public class UserService {
////
////    @Autowired
////    private UserRepository userRepository;
////
////    public User getUserById(Long id) {
////        return userRepository.findById(id)
////                .orElseThrow(() -> new NotFoundException("User not found"));
////    }
////
////    public User updateUser(Long id, User updates) {
////        User user = getUserById(id);
////        if (updates.getName() != null) user.setName(updates.getName());
////        if (updates.getEmail() != null) user.setEmail(updates.getEmail());
////        return userRepository.save(user);
////    }
////}
//package com.example.quizhive.service;
//
//import com.example.quizhive.exception.NotFoundException;
//import com.example.quizhive.model.User;
//import com.example.quizhive.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public User getUserInfo(User user) {
//        return userRepository.findById(user.getId())
//                .orElseThrow(() -> new NotFoundException("User not found"));
//    }
//
//    public User getUserInfoById(String userId) {
//        return userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException("User not found"));
//    }
//
//    public List<User> getLeaderboard(int limit) {
//        return userRepository.findTopUsersByScore(PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "score")));
//    }
//}
package com.example.quizhive.service;

import com.example.quizhive.exception.NotFoundException;
import com.example.quizhive.model.User;
import com.example.quizhive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieve user information by ID.
     * @param id The ID of the user.
     * @return The user object.
     */
    public User getUserInfo(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    /**
     * Retrieve all users.
     * @return List of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Find user by ID.
     * @param id The ID of the user.
     * @return Optional containing the user object if found.
     */
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    /**
     * Retrieve the top N users sorted by score.
     * @param n Number of top users to retrieve.
     * @return List of top N users.
     */
    public List<User> getTopUsersByScore(int n) {
        return userRepository.findTopUsersByScore(PageRequest.of(0, n));
    }

    /**
     * Retrieve the scoreboard for the top N users along with the rank of a specific user.
     * @param userId The ID of the user requesting the scoreboard.
     * @param n Number of top users to include in the scoreboard.
     * @return A response containing the scoreboard and the user's rank.
     */
    public ScoreboardResponse getScoreboard(String userId, int n) {
        List<User> allUsers = userRepository.findAll();
        List<User> topUsers = allUsers.stream()
                .sorted((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()))
                .limit(n)
                .collect(Collectors.toList());

        User loggedInUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        long rank = allUsers.stream()
                .filter(user -> user.getScore() > loggedInUser.getScore())
                .count() + 1;

        RankedUser userRank = new RankedUser(loggedInUser, (int) rank);

        return new ScoreboardResponse(topUsers, userRank);
    }

    /**
     * Inner class for the scoreboard response.
     */
    public static class ScoreboardResponse {
        private List<User> scoreboard;
        private RankedUser userRank;

        public ScoreboardResponse(List<User> scoreboard, RankedUser userRank) {
            this.scoreboard = scoreboard;
            this.userRank = userRank;
        }

        // Getters and setters
        public List<User> getScoreboard() {
            return scoreboard;
        }

        public void setScoreboard(List<User> scoreboard) {
            this.scoreboard = scoreboard;
        }

        public RankedUser getUserRank() {
            return userRank;
        }

        public void setUserRank(RankedUser userRank) {
            this.userRank = userRank;
        }
    }

    /**
     * Inner class for representing a ranked user.
     */
    public static class RankedUser {
        private User user;
        private int rank;

        public RankedUser(User user, int rank) {
            this.user = user;
            this.rank = rank;
        }

        // Getters and setters
        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }
    }
}
