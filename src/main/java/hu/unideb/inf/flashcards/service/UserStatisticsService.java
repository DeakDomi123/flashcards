package hu.unideb.inf.flashcards.service;

import hu.unideb.inf.flashcards.service.dto.UserStatisticsDTO;

import java.util.List;

public interface UserStatisticsService {
    UserStatisticsDTO save(UserStatisticsDTO dto);
    List<UserStatisticsDTO> findAll();
    UserStatisticsDTO findById(Long id);
    UserStatisticsDTO update(UserStatisticsDTO dto);
    UserStatisticsDTO findByUserId(Long userId);
}