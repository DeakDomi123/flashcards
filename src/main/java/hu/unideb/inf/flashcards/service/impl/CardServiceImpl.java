package hu.unideb.inf.flashcards.service.impl;

import hu.unideb.inf.flashcards.data.entity.CardEntity;
import hu.unideb.inf.flashcards.data.entity.DeckEntity;
import hu.unideb.inf.flashcards.data.entity.UserEntity;
import hu.unideb.inf.flashcards.data.repository.CardRepository;
import hu.unideb.inf.flashcards.data.repository.DeckRepository;
import hu.unideb.inf.flashcards.service.CardService;
import hu.unideb.inf.flashcards.service.CommonService;
import hu.unideb.inf.flashcards.service.dto.CardDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository repo;

    @Autowired
    ModelMapper mapper;

    @Autowired
    DeckRepository deckRepo;

    @Override
    public CardDTO save(CardDTO dto) {
        var entity = mapper.map(dto, CardEntity.class);
        entity.setDeck(deckRepo.findById(dto.getDeckId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Deck not found")));
        entity = repo.save(entity);
        return mapper.map(entity, CardDTO.class);
    }

    @Override
    public CardDTO findById(Long id) {
        var entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found with id: " + id));
        return mapper.map(entity, CardDTO.class);
    }

    @Override
    public CardDTO update(CardDTO cardDTO) {
        var existingEntity = repo.findById(cardDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found"));
        existingEntity.setQuestion(cardDTO.getQuestion());
        existingEntity.setAnswer(cardDTO.getAnswer());
        existingEntity.setLearned(cardDTO.isLearned());

        var updatedEntity = repo.save(existingEntity);
        return mapper.map(updatedEntity, CardDTO.class);
    }

    @Override
    public List<CardDTO> getCardsByUser(UserEntity user) {
        var userDecks = deckRepo.findAllByUserId(user.getId());
        var cards = userDecks.stream()
                .map(DeckEntity::getId)
                .map(repo::findByDeckId)
                .flatMap(List::stream)
                .toList();
        return cards.stream()
                .map(entity -> mapper.map(entity, CardDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found with id: " + id);
        repo.deleteById(id);
    }

    @Override
    public List<CardDTO> getCardsByDeckId(Long deckId) {
        var entities = repo.findByDeckId(deckId);
        return entities.stream()
                .map(entity -> mapper.map(entity, CardDTO.class))
                .collect(Collectors.toList());
    }
}
