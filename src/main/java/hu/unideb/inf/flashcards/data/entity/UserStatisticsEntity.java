package hu.unideb.inf.flashcards.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_statistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserStatisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "total_decks_created", nullable = false, columnDefinition = "integer default 0")
    private int totalDecksCreated;

    @Column(name = "total_cards_studied", nullable = false, columnDefinition = "integer default 0")
    private int totalCardsStudied;

    @Column(name = "correct_answers", nullable = false, columnDefinition = "integer default 0")
    private int correctAnswers;

    @Column(name = "incorrect_answers", nullable = false, columnDefinition = "integer default 0")
    private int incorrectAnswers;

    @Column(name = "total_study_time", nullable = false, columnDefinition = "integer default 0")
    private int totalStudyTime;
}
