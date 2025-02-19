package hu.unideb.inf.flashcards.data.repository;

import hu.unideb.inf.flashcards.data.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findByDeckId(Long deckId);
}
