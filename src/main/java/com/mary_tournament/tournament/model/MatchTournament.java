package com.mary_tournament.tournament.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "match_tournament")
public class MatchTournament {
    @Id
    @ColumnDefault("nextval('match_tournament_id_match_seq')")
    @Column(name = "id_match", nullable = false)
    private Integer id;

    @ColumnDefault("0")
    @Column(name = "score_p1")
    private Integer scoreP1;

    @ColumnDefault("0")
    @Column(name = "score_p2")
    private Integer scoreP2;

    @Column(name = "match_ref", nullable = false, length = 50)
    private String matchRef;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_1", nullable = false)
    private User player1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_2", nullable = false)
    private User player2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner")
    private User winner;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tournament", nullable = false)
    private Tournament idTournament;

}