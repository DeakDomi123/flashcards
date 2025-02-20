package hu.unideb.inf.flashcards.service;

import hu.unideb.inf.flashcards.service.dto.StudySessionDTO;

import java.util.List;

public interface StudySessionService {
    StudySessionDTO save(StudySessionDTO dto);
    List<StudySessionDTO> findAll();
    StudySessionDTO findById(Long id);
    List<StudySessionDTO> getStudySessionsByUserId(Long userId);
    List<StudySessionDTO> getStudySessionsByDeckId(Long deckId);
    StudySessionDTO update(StudySessionDTO dto);
}