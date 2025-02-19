package hu.unideb.inf.flashcards.data.repository;

import hu.unideb.inf.flashcards.data.entity.DeckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<DeckEntity, Long> {
    List<DeckEntity> findAllByUserId(Long userId);
    DeckEntity findByName(String deckName);
}