package hu.unideb.inf.flashcards.service;

import hu.unideb.inf.flashcards.service.dto.DeckDTO;

import java.util.List;

public interface DeckService {
    DeckDTO save(DeckDTO dto);
    DeckDTO findById(Long id);
    DeckDTO update(DeckDTO deckDTO);
    void delete(Long id);
    List<DeckDTO> findAll();

    List<DeckDTO> getDecksByUserId(Long userId);
    List<DeckDTO> findByName(String name);
}
