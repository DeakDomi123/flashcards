package hu.unideb.inf.flashcards.data.repository;

import hu.unideb.inf.flashcards.data.entity.UserStatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatisticsRepository extends JpaRepository<UserStatisticsEntity, Long> {
    UserStatisticsEntity findByUserId(Long userId);
}