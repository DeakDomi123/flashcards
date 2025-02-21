package hu.unideb.inf.flashcards.service;

import hu.unideb.inf.flashcards.data.entity.UserEntity;
import hu.unideb.inf.flashcards.service.dto.StudySessionDTO;

import java.util.List;

public interface StudySessionService {
    StudySessionDTO save(StudySessionDTO dto, UserEntity user);
    StudySessionDTO findById(Long id);
    List<StudySessionDTO> getStudySessionsByUser(UserEntity user);
    List<StudySessionDTO> getStudySessionsByDeckId(Long deckId);
    StudySessionDTO update(StudySessionDTO dto);
}