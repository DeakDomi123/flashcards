package hu.unideb.inf.flashcards.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "decks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeckEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String name;

    private String description;

    private LocalDateTime dueDate;
}
