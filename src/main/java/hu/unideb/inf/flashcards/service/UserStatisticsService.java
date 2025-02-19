package hu.unideb.inf.flashcards.service;

import hu.unideb.inf.flashcards.service.dto.UserStatisticsDTO;

import java.util.List;

public interface UserStatisticsService {
    UserStatisticsDTO getUserStatisticsById(Long id);
    UserStatisticsDTO createUserStatistics(UserStatisticsDTO dto);
    UserStatisticsDTO updateUserStatistics(Long id, UserStatisticsDTO dto);
    void deleteUserStatistics(Long id);
    List<UserStatisticsDTO> getAllUserStatistics();

    // Opcionálisan: lekérhetjük egy user-hez tartozó statisztikákat
    UserStatisticsDTO getUserStatisticsByUserId(Long userId);
}