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
public class StudySessionDTO {
    private Long id;
    private Long deckId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int correctAnswers;
    private int incorrectAnswers;
}