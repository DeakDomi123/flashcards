package hu.unideb.inf.flashcards.service;

import hu.unideb.inf.flashcards.service.dto.CardDTO;

import java.util.List;

public interface CardService {
    CardDTO getCardById(Long id);
    CardDTO createCard(CardDTO cardDTO);
    CardDTO updateCard(Long id, CardDTO cardDTO);
    void deleteCard(Long id);
    List<CardDTO> getAllCards();

    List<CardDTO> getCardsByDeckId(Long deckId);
}