package hu.unideb.inf.flashcards.service.impl;

import hu.unideb.inf.flashcards.data.entity.UserStatisticsEntity;
import hu.unideb.inf.flashcards.data.repository.UserRepository;
import hu.unideb.inf.flashcards.data.repository.UserStatisticsRepository;
import hu.unideb.inf.flashcards.service.UserStatisticsService;
import hu.unideb.inf.flashcards.service.dto.UserStatisticsDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStatisticsServiceImpl implements UserStatisticsService {

    @Autowired
    private UserStatisticsRepository repo;

    @Autowired
    ModelMapper mapper;

    @Autowired
    UserRepository userRepo;

    @Override
    public UserStatisticsDTO save(UserStatisticsDTO dto) {
        UserStatisticsEntity entity = mapper.map(dto, UserStatisticsEntity.class);
        entity.setUser(userRepo.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        entity = repo.save(entity);
        return mapper.map(entity, UserStatisticsDTO.class);
    }

    @Override
    public List<UserStatisticsDTO> findAll() {
        List<UserStatisticsEntity> entities = repo.findAll();
        return mapper.map(entities, new TypeToken<List<UserStatisticsDTO>>(){}.getType());
    }

    @Override
    public UserStatisticsDTO findById(Long id) {
        UserStatisticsDTO dto = new UserStatisticsDTO();
        UserStatisticsEntity entity = repo.getReferenceById(id);

        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setTotalDecksCreated(entity.getTotalDecksCreated());
        dto.setTotalCardsStudied(entity.getTotalCardsStudied());
        dto.setCorrectAnswers(entity.getCorrectAnswers());
        dto.setIncorrectAnswers(entity.getIncorrectAnswers());
        dto.setTotalStudyTime(entity.getTotalStudyTime());

        return dto;
    }

    @Override
    public UserStatisticsDTO update(UserStatisticsDTO dto) {
        UserStatisticsEntity existingEntity = repo.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Study Session not found"));

        existingEntity.setTotalDecksCreated(dto.getTotalDecksCreated());
        existingEntity.setTotalCardsStudied(dto.getTotalCardsStudied());
        existingEntity.setCorrectAnswers(dto.getCorrectAnswers());
        existingEntity.setIncorrectAnswers(dto.getIncorrectAnswers());
        existingEntity.setTotalStudyTime(dto.getTotalStudyTime());


        UserStatisticsEntity updatedEntity = repo.save(existingEntity);
        return mapper.map(updatedEntity, UserStatisticsDTO.class);
    }

    @Override
    public UserStatisticsDTO findByUserId(Long userId) {
        UserStatisticsEntity entity = repo.findByUserId(userId);
        return mapper.map(entity, UserStatisticsDTO.class);
    }
}
