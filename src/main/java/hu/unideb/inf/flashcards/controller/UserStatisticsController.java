package hu.unideb.inf.flashcards.controller;

import hu.unideb.inf.flashcards.service.CommonService;
import hu.unideb.inf.flashcards.service.UserStatisticsService;
import hu.unideb.inf.flashcards.service.dto.UserStatisticsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-statistics")
public class UserStatisticsController {

    @Autowired
    UserStatisticsService userStatisticsService;

    @Autowired
    CommonService commonService;

    @PostMapping
    public ResponseEntity<UserStatisticsDTO> createUserStatistics(@RequestBody UserStatisticsDTO dto,
                                                                  @AuthenticationPrincipal UserDetails userDetails) {
        var user = commonService.getCurrentUser(userDetails);
        return ResponseEntity.ok(userStatisticsService.save(dto, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserStatisticsDTO> getUserStatisticsById(@PathVariable Long id) {
        return ResponseEntity.ok(userStatisticsService.findById(id));
    }

    @PutMapping
    public ResponseEntity<UserStatisticsDTO> updateUserStatistics(@RequestBody UserStatisticsDTO dto,
                                                                  @AuthenticationPrincipal UserDetails userDetails) {
        var user = commonService.getCurrentUser(userDetails);
        return ResponseEntity.ok(userStatisticsService.update(dto, user));
    }

    @GetMapping
    public ResponseEntity<UserStatisticsDTO> getUserStatisticsByUser(@AuthenticationPrincipal UserDetails userDetails) {
        var user = commonService.getCurrentUser(userDetails);
        return ResponseEntity.ok(userStatisticsService.findByUser(user));
    }
}