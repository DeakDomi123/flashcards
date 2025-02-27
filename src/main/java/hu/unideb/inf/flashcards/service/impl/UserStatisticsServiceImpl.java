package hu.unideb.inf.flashcards.service.impl;

import hu.unideb.inf.flashcards.data.entity.UserEntity;
import hu.unideb.inf.flashcards.data.entity.UserStatisticsEntity;
import hu.unideb.inf.flashcards.data.repository.UserStatisticsRepository;
import hu.unideb.inf.flashcards.service.UserStatisticsService;
import hu.unideb.inf.flashcards.service.dto.UserStatisticsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserStatisticsServiceImpl implements UserStatisticsService {

    @Autowired
    private UserStatisticsRepository repo;

    @Autowired
    ModelMapper mapper;

    private UserStatisticsDTO mapToDTO(UserStatisticsEntity entity) {
        return mapper.map(entity, UserStatisticsDTO.class);
    }

    @Override
    public UserStatisticsDTO save(UserStatisticsDTO dto, UserEntity user) {
        var existingEntity = repo.findByUserId(user.getId());
        if (existingEntity != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User statistics already exists for user with id: " + user.getId());
        var entity = mapper.map(dto, UserStatisticsEntity.class);
        entity.setUser(user);
        entity = repo.save(entity);
        return mapper.map(entity, UserStatisticsDTO.class);
    }

    @Override
    public UserStatisticsDTO findById(Long id) {
        var entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User statistic not found with id: " + id));
        return mapToDTO(entity);
    }

    @Override
    public UserStatisticsDTO update(UserStatisticsDTO dto, UserEntity user) {
        var existingEntity = repo.findByUserId(user.getId());
        if (existingEntity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User statistic not found with userId: " + user.getId());

        existingEntity.setTotalCardsStudied(dto.getTotalCardsStudied());
        existingEntity.setCorrectAnswers(dto.getCorrectAnswers());
        existingEntity.setIncorrectAnswers(dto.getIncorrectAnswers());
        existingEntity.setTotalStudyTime(dto.getTotalStudyTime());

        var updatedEntity = repo.save(existingEntity);
        return mapper.map(updatedEntity, UserStatisticsDTO.class);
    }

    @Override
    public UserStatisticsDTO findByUser(UserEntity user) {
        var entity = repo.findByUserId(user.getId());
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User statistic not with userId: " + user.getId());
        return mapToDTO(entity);
    }
}
