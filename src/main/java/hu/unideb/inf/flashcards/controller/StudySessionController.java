package hu.unideb.inf.flashcards.controller;

import hu.unideb.inf.flashcards.service.StudySessionService;
import hu.unideb.inf.flashcards.service.dto.StudySessionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study-sessions")
public class StudySessionController {

    private final StudySessionService studySessionService;

    public StudySessionController(StudySessionService studySessionService) {
        this.studySessionService = studySessionService;
    }

    @PostMapping
    public ResponseEntity<StudySessionDTO> createStudySession(@RequestBody StudySessionDTO dto) {
        return ResponseEntity.ok(studySessionService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<StudySessionDTO>> getAllStudySessions() {
        return ResponseEntity.ok(studySessionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudySessionDTO> getStudySessionById(@PathVariable Long id) {
        return ResponseEntity.ok(studySessionService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StudySessionDTO>> getStudySessionsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(studySessionService.getStudySessionsByUserId(userId));
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