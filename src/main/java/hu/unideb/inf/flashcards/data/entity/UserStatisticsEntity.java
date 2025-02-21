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
    @JoinColumn(name = "user_id", nullable = false, unique = true, referencedColumnName = "id")
    private UserEntity user;

    @Column(nullable = false)
    private int totalDecksCreated;

    @Column(nullable = false)
    private int totalCardsStudied;

    @Column(nullable = false)
    private int correctAnswers;

    @Column(nullable = false)
    private int incorrectAnswers;

    @Column(nullable = false)
    private int totalStudyTime;
}
