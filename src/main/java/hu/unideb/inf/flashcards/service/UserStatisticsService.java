package hu.unideb.inf.flashcards.service;

import hu.unideb.inf.flashcards.data.entity.UserEntity;
import hu.unideb.inf.flashcards.service.dto.UserStatisticsDTO;

public interface UserStatisticsService {
    UserStatisticsDTO save(UserStatisticsDTO dto, UserEntity user);
    UserStatisticsDTO findById(Long id);
    UserStatisticsDTO update(UserStatisticsDTO dto, UserEntity user);
    UserStatisticsDTO findByUser(UserEntity user);
}