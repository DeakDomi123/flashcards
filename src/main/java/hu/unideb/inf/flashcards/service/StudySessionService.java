package hu.unideb.inf.flashcards.service;

import hu.unideb.inf.flashcards.service.dto.StudySessionDTO;

import java.util.List;

public interface StudySessionService {
    StudySessionDTO getStudySessionById(Long id);
    StudySessionDTO createStudySession(StudySessionDTO dto);
    StudySessionDTO updateStudySession(Long id, StudySessionDTO dto);
    void deleteStudySession(Long id);
    List<StudySessionDTO> getAllStudySessions();

    // Opcionálisan: lekérhetjük egy user-hez tartozó study session-öket
    List<StudySessionDTO> getStudySessionsByUserId(Long userId);
    // Opcionálisan: lekérhetjük egy deck-hez tartozó study session-öket
    List<StudySessionDTO> getStudySessionsByDeckId(Long deckId);
}