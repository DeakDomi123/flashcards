package hu.unideb.inf.flashcards.service;

import hu.unideb.inf.flashcards.service.dto.CardDTO;

import java.util.List;

public interface CardService {
    CardDTO save(CardDTO dto);
    CardDTO findById(Long id);
    CardDTO update(CardDTO cardDTO);
    void delete(Long id);
    List<CardDTO> findAll();
    List<CardDTO> getCardsByDeckId(Long deckId);
}