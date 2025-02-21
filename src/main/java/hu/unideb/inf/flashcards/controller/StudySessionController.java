package hu.unideb.inf.flashcards.controller;

import hu.unideb.inf.flashcards.service.CommonService;
import hu.unideb.inf.flashcards.service.StudySessionService;
import hu.unideb.inf.flashcards.service.dto.StudySessionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study-sessions")
public class StudySessionController {

    @Autowired
    StudySessionService studySessionService;

    @Autowired
    CommonService commonService;

    @PostMapping
    public ResponseEntity<StudySessionDTO> createStudySession(@RequestBody StudySessionDTO dto,
                                                              @AuthenticationPrincipal UserDetails userDetails) {
        var user = commonService.getCurrentUser(userDetails);
        return ResponseEntity.ok(studySessionService.save(dto, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudySessionDTO> getStudySessionById(@PathVariable Long id) {
        return ResponseEntity.ok(studySessionService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<StudySessionDTO>> getStudySessionsByUser(@AuthenticationPrincipal UserDetails userDetails) {
        var user = commonService.getCurrentUser(userDetails);
        return ResponseEntity.ok(studySessionService.getStudySessionsByUser(user));
    }

    @GetMapping("/deck/{deckId}")
    public ResponseEntity<List<StudySessionDTO>> getStudySessionsByDeckId(@PathVariable Long deckId) {
        return ResponseEntity.ok(studySessionService.getStudySessionsByDeckId(deckId));
    }

    @PutMapping
    public ResponseEntity<StudySessionDTO> updateStudySession(@RequestBody StudySessionDTO dto) {
        return ResponseEntity.ok(studySessionService.update(dto));
    }
}