package hu.unideb.inf.flashcards.service.impl;

import hu.unideb.inf.flashcards.data.entity.StudySessionsEntity;
import hu.unideb.inf.flashcards.data.entity.UserEntity;
import hu.unideb.inf.flashcards.data.repository.DeckRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import hu.unideb.inf.flashcards.data.repository.StudySessionRepository;
import hu.unideb.inf.flashcards.service.StudySessionService;
import hu.unideb.inf.flashcards.service.dto.StudySessionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudySessionServiceImpl implements StudySessionService {

    @Autowired
    StudySessionRepository repo;

    @Autowired
    ModelMapper mapper;

    @Autowired
    DeckRepository deckRepo;

    private StudySessionDTO mapToDTO(StudySessionsEntity entity) {
        return mapper.map(entity, StudySessionDTO.class);
    }

    @Override
    public StudySessionDTO save(StudySessionDTO dto, UserEntity user) {
        var entity = mapper.map(dto, StudySessionsEntity.class);
        entity.setUser(user);
        entity.setDeck(deckRepo.findById(dto.getDeckId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Deck not found")));
        entity = repo.save(entity);
        return mapper.map(entity, StudySessionDTO.class);
    }

    @Override
    public StudySessionDTO findById(Long id) {
        var entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Study session not found with id: " + id));
        return mapToDTO(entity);
    }

    @Override
    public List<StudySessionDTO> getStudySessionsByUser(UserEntity user) {
        var entities = repo.findByUserId(user.getId());
        return entities.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<StudySessionDTO> getStudySessionsByDeckId(Long deckId) {
        var entities = repo.findByDeckId(deckId);
        return entities.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public StudySessionDTO update(StudySessionDTO dto) {
        var existingEntity = repo.findById(dto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Study session not found"));

        existingEntity.setStartTime(dto.getStartTime());
        existingEntity.setEndTime(dto.getEndTime());
        existingEntity.setCorrectAnswers(dto.getCorrectAnswers());


        var updatedEntity = repo.save(existingEntity);
        return mapper.map(updatedEntity, StudySessionDTO.class);
    }
}