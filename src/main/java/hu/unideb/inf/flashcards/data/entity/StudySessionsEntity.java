package hu.unideb.inf.flashcards.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "study_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudySessionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "deck_id", nullable = false)
    private DeckEntity deck;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "correct_answers", nullable = false, columnDefinition = "integer default 0")
    private int correctAnswers;

    @Column(name = "incorrect_answers", nullable = false, columnDefinition = "integer default 0")
    private int incorrectAnswers;
}

