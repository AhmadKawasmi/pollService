package com.pollServiceProject.controller;

import com.pollServiceProject.model.PollAnswer;
import com.pollServiceProject.model.PollAnswerRequest;
import com.pollServiceProject.service.PollAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * Controller for handling poll answer operations.
 */
@CrossOrigin(origins = "http://localhost:8082") // Allow frontend calls

@RestController
@RequestMapping("/poll-answer")
public class PollAnswerController {

    @Autowired
    private PollAnswerService pollAnswerService;

    /**
     * Creates a new poll answer.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createPollAnswer(@RequestBody PollAnswerRequest pollAnswerRequest) {
        pollAnswerService.createPollAnswer(pollAnswerRequest);
        return ResponseEntity.ok("User answer CREATED successfully.");
    }

    /**
     * Deletes a poll answer by its ID.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePollAnswer(@PathVariable Long id) {
        pollAnswerService.deletePollAnswerById(id);
        return ResponseEntity.ok("User Answer DELETED successfully.");
    }

    /**
     * Gets a poll answer by its ID.
     */
    @GetMapping("/answer/{id}")
    public PollAnswer getAnswerById(@PathVariable Long id) {
        return pollAnswerService.getPollAnswerById(id);
    }

    /**
     * Gets the count of each option for a specific question.
     */
    @GetMapping("/option-count/{questionId}")
    public ResponseEntity<List<Map<String, Object>>> getOptionCountByQuestionId(@PathVariable Long questionId) {
        List<Map<String, Object>> optionCountList = pollAnswerService.getOptionCountByQuestionId(questionId);
        return ResponseEntity.ok(optionCountList);
    }

    /**
     * Gets the number of users who answered a specific question.
     */
    @GetMapping("/users-answered-count/{questionId}")
    public ResponseEntity<String> getUsersAnsweredCountForQuestion(@PathVariable Long questionId) {
        Long userCount = pollAnswerService.countUniqueUsersByQuestionId(questionId);
        return ResponseEntity.ok("Total users who answered question with ID " + questionId + " is : " + userCount);
    }

    /**
     * Gets all answers submitted by a specific user.
     */
    @GetMapping("/user-answers/{userId}")
    public List<PollAnswer> getAllUserAnswersByUserId(@PathVariable Long userId) {
        return pollAnswerService.getAllPollAnswersByUserId(userId);
    }

    /**
     * Gets the total number of answers submitted by a specific user.
     */
    @GetMapping("/user-answer-count/{userId}")
    public ResponseEntity<String> getUserAnswerCount(@PathVariable Long userId) {
        long answerCount = pollAnswerService.getAnswerCountByUserId(userId);
        return ResponseEntity.ok("Total answers given by user with ID " + userId + " is : " + answerCount);
    }

    /**
     * Deletes all answers of a specific user.
     */
    @DeleteMapping("/delete-user-answers/{userId}")
    public ResponseEntity<String> deleteUserAnswers(@PathVariable Long userId) {
        pollAnswerService.deleteAllUserAnswers(userId);
        return ResponseEntity.ok("All answers of user with ID " + userId + " have been deleted.");
    }

    /**
     * Gets option counts for all questions.
     */
    @GetMapping("/option-counts-for-all-questions")
    public ResponseEntity<List<Map<String, Object>>> getOptionCountsForAllQuestions() {
        List<Map<String, Object>> optionCounts = pollAnswerService.getOptionCountsForAllQuestions();
        return ResponseEntity.ok(optionCounts);
    }
}
