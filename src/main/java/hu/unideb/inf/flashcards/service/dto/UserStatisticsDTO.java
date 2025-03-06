package hu.unideb.inf.flashcards.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserStatisticsDTO {
    private Long id;
    private int totalCardsStudied;
    private int correctAnswers;
    private int unsureAnswers;
    private int incorrectAnswers;
    private int totalStudyTime;
}