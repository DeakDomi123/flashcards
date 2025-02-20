package hu.unideb.inf.flashcards.service.impl;

import hu.unideb.inf.flashcards.data.entity.CardEntity;
import hu.unideb.inf.flashcards.data.repository.CardRepository;
import hu.unideb.inf.flashcards.data.repository.DeckRepository;
import hu.unideb.inf.flashcards.data.repository.UserRepository;
import hu.unideb.inf.flashcards.service.CardService;
import hu.unideb.inf.flashcards.service.dto.CardDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        CardEntity entity = mapper.map(dto, CardEntity.class);
        entity.setDeck(deckRepo.findById(dto.getDeckId()).orElseThrow(() -> new RuntimeException("Deck not found")));
        entity = repo.save(entity);
        return mapper.map(entity, CardDTO.class);
    }

    @Override
    public CardDTO findById(Long id) {
        CardDTO dto = new CardDTO();
        CardEntity entity = repo.getReferenceById(id);

        dto.setId(entity.getId());
        dto.setQuestion(entity.getQuestion());
        dto.setAnswer(entity.getAnswer());
        dto.setDeckId(entity.getDeck().getId());
        return dto;
    }

    @Override
    public CardDTO update(CardDTO cardDTO) {
        CardEntity existingEntity = repo.findById(cardDTO.getId()).orElseThrow(() -> new RuntimeException("Card not found"));
        existingEntity.setQuestion(cardDTO.getQuestion());
        existingEntity.setAnswer(cardDTO.getAnswer());
        existingEntity.setHint(cardDTO.getHint());

        CardEntity updatedEntity = repo.save(existingEntity);
        return mapper.map(updatedEntity, CardDTO.class);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<CardDTO> findAll() {
        List<CardEntity> entities = repo.findAll();
        return mapper.map(entities, new TypeToken<List<CardDTO>>(){}.getType());
    }

    @Override
    public List<CardDTO> getCardsByDeckId(Long deckId) {
        List<CardEntity> filtered;
        filtered = repo.findAll()
                .stream()
                .filter(x -> x.getDeck().getId().equals(deckId))
                .toList();

        return mapper.map(filtered, new TypeToken<List<CardDTO>>(){}.getType());
    }
}
