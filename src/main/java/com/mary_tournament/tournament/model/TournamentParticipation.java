package com.mary_tournament.tournament.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tournament_participation")
public class TournamentParticipation {
    @EmbeddedId
    private TournamentParticipationId id;

    @MapsId("idUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

    @MapsId("idTournament")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tournament", nullable = false)
    private Tournament idTournament;

}