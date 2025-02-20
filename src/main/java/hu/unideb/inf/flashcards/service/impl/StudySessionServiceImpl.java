package hu.unideb.inf.flashcards.service.impl;

import hu.unideb.inf.flashcards.data.entity.StudySessionsEntity;
import hu.unideb.inf.flashcards.data.repository.DeckRepository;
import hu.unideb.inf.flashcards.data.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import hu.unideb.inf.flashcards.data.repository.StudySessionRepository;
import hu.unideb.inf.flashcards.service.StudySessionService;
import hu.unideb.inf.flashcards.service.dto.StudySessionDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class StudySessionServiceImpl implements StudySessionService {

    @Autowired
    StudySessionRepository repo;

    @Autowired
    ModelMapper mapper;

    @Autowired
    DeckRepository deckRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public StudySessionDTO save(StudySessionDTO dto) {
        StudySessionsEntity entity = mapper.map(dto, StudySessionsEntity.class);
        entity.setUser(userRepo.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        entity.setDeck(deckRepo.findById(dto.getDeckId()).orElseThrow(() -> new RuntimeException("Deck not found")));
        entity = repo.save(entity);
        return mapper.map(entity, StudySessionDTO.class);
    }

    @Override
    public List<StudySessionDTO> findAll() {
        List<StudySessionsEntity> entities = repo.findAll();
        return mapper.map(entities, new TypeToken<List<StudySessionDTO>>(){}.getType());
    }

    @Override
    public StudySessionDTO findById(Long id) {
        StudySessionDTO dto = new StudySessionDTO();
        StudySessionsEntity entity = repo.getReferenceById(id);

        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setDeckId(entity.getDeck().getId());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setCorrectAnswers(entity.getCorrectAnswers());
        dto.setIncorrectAnswers(entity.getIncorrectAnswers());
        return dto;
    }

    @Override
    public List<StudySessionDTO> getStudySessionsByUserId(Long userId) {
        List<StudySessionsEntity> filtered;
        filtered = repo.findAll()
                .stream()
                .filter(x -> x.getUser().getId().equals(userId))
                .toList();

        return mapper.map(filtered, new TypeToken<List<StudySessionDTO>>(){}.getType());
    }

    @Override
    public List<StudySessionDTO> getStudySessionsByDeckId(Long deckId) {
        List<StudySessionsEntity> filtered;
        filtered = repo.findAll()
                .stream()
                .filter(x -> x.getDeck().getId().equals(deckId))
                .toList();

        return mapper.map(filtered, new TypeToken<List<StudySessionDTO>>(){}.getType());
    }

    @Override
    public StudySessionDTO update(StudySessionDTO dto) {
        StudySessionsEntity existingEntity = repo.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Study Session not found"));

        existingEntity.setStartTime(dto.getStartTime());
        existingEntity.setEndTime(dto.getEndTime());
        existingEntity.setCorrectAnswers(dto.getCorrectAnswers());


        StudySessionsEntity updatedEntity = repo.save(existingEntity);
        return mapper.map(updatedEntity, StudySessionDTO.class);
    }
}