package hu.unideb.inf.flashcards.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;


@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "deck_id", nullable = false, referencedColumnName = "id")
    private DeckEntity deck;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

    @Min(1)
    @Max(3)
    @Column(nullable = false)
    private Integer learnProgress;    
}