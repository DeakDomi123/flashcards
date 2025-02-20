package hu.unideb.inf.flashcards.controller;

import hu.unideb.inf.flashcards.service.UserStatisticsService;
import hu.unideb.inf.flashcards.service.dto.UserStatisticsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-statistics")
public class UserStatisticsController {

    private final UserStatisticsService userStatisticsService;

    public UserStatisticsController(UserStatisticsService userStatisticsService) {
        this.userStatisticsService = userStatisticsService;
    }

    @PostMapping
    public ResponseEntity<UserStatisticsDTO> createUserStatistics(@RequestBody UserStatisticsDTO dto) {
        return ResponseEntity.ok(userStatisticsService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserStatisticsDTO>> getAllUserStatistics() {
        return ResponseEntity.ok(userStatisticsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserStatisticsDTO> getUserStatisticsById(@PathVariable Long id) {
        return ResponseEntity.ok(userStatisticsService.findById(id));
    }

    @PutMapping
    public ResponseEntity<UserStatisticsDTO> updateUserStatistics(@RequestBody UserStatisticsDTO dto) {
        return ResponseEntity.ok(userStatisticsService.update(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserStatisticsDTO> getUserStatisticsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userStatisticsService.findByUserId(userId));
    }
}