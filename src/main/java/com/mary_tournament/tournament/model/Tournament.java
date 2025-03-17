package com.mary_tournament.tournament.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tournament", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Setter
    @Getter
    @Column(name = "datestart", nullable = false)
    private LocalDate datestart;

    @Column(name = "dateend", nullable = false)
    private LocalDate dateend;

    @ColumnDefault("false")
    @Column(name = "ispaid")
    private Boolean ispaid;

    @ColumnDefault("0")
    @Column(name = "price")
    private Integer price;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_category", nullable = false)
    private CategoryGame idCategory;

    @ColumnDefault("32")
    @Column(name = "max_players")
    private Integer maxPlayers;

    @Column(name = "registration_deadline", nullable = false)
    private LocalDate registrationDeadline;

    @OneToMany(mappedBy = "idTournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TournamentParticipation> participants = new ArrayList<>();



}