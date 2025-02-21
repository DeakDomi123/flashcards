package hu.unideb.inf.flashcards.service;

import hu.unideb.inf.flashcards.data.entity.UserEntity;
import hu.unideb.inf.flashcards.service.dto.DeckDTO;

import java.util.List;

public interface DeckService {
    DeckDTO save(DeckDTO dto, UserEntity user);
    DeckDTO findById(Long id);
    DeckDTO update(DeckDTO deckDTO);
    void delete(Long id);

    List<DeckDTO> getDecksByUser(UserEntity user);
    DeckDTO findByName(String name);
}
