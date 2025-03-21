package hu.unideb.inf.flashcards.service.impl;

import hu.unideb.inf.flashcards.data.entity.DeckEntity;
import hu.unideb.inf.flashcards.data.entity.UserEntity;
import hu.unideb.inf.flashcards.data.repository.DeckRepository;
import hu.unideb.inf.flashcards.service.DeckService;
import hu.unideb.inf.flashcards.service.dto.DeckDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeckServiceImpl implements DeckService {

    @Autowired
    DeckRepository repo;

    @Autowired
    ModelMapper mapper;

    @Override
    public DeckDTO save(DeckDTO dto, UserEntity user) {
        var userDecks = repo.findAllByUserId(user.getId());
        var existingEntity = userDecks.stream()
                .filter(deckEntity -> deckEntity.getName().equals(dto.getName()))
                .findFirst()
                .orElse(null);
        if (existingEntity != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Deck already exists with name: " + dto.getName());
        var entity = mapper.map(dto, DeckEntity.class);
        entity.setUser(user);
        entity = repo.save(entity);
        return mapper.map(entity, DeckDTO.class);
    }

    @Override
    public List<DeckDTO> getDecksByUser(UserEntity user) {
        var entities = repo.findAllByUserId(user.getId());
        return entities.stream()
                .map(entity -> mapper.map(entity, DeckDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DeckDTO findByName(String name) {
        var entity = repo.findByName(name);
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deck not found with name: " + name);
        return mapper.map(entity, DeckDTO.class);
    }

    @Override
    public DeckDTO findById(Long id) {
        var entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Deck not found with id: " + id));
        return mapper.map(entity, DeckDTO.class);
    }

    @Override
    public DeckDTO update(DeckDTO dto) {
        var existingEntity = repo.findById(dto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Deck not found"));

        if (dto.getName() != null)
            existingEntity.setName(dto.getName());
        existingEntity.setLearned(dto.isLearned());
        existingEntity.setDescription(dto.getDescription());
        existingEntity.setDueDate(dto.getDueDate());

        var updatedEntity = repo.save(existingEntity);
        return mapper.map(updatedEntity, DeckDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deck not found with id: " + id);
        repo.deleteById(id);
    }
}
