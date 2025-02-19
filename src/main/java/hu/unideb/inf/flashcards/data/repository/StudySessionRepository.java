package hu.unideb.inf.flashcards.data.repository;

import hu.unideb.inf.flashcards.data.entity.StudySessionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudySessionRepository extends JpaRepository<StudySessionsEntity, Long> {
    List<StudySessionsEntity> findByUserId(Long userId);
    List<StudySessionsEntity> findByDeckId(Long deckId);
}