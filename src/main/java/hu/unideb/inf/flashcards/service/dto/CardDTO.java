package hu.unideb.inf.flashcards.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Long id;
    private Long deckId;
    private String question;
    private String answer;
    private Integer learnProgress;
}