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
public class DeckDTO {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private LocalDateTime dueDate;
}