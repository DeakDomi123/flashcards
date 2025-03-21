package hu.unideb.inf.flashcards.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeckDTO {
    private Long id;
    private String name;
    private String description;
    private boolean isLearned;
    private Date dueDate;
}