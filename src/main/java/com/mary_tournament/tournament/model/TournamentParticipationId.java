package com.mary_tournament.tournament.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TournamentParticipationId implements Serializable {
    private static final long serialVersionUID = -8224834143605344140L;
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(name = "id_tournament", nullable = false)
    private Long idTournament;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TournamentParticipationId entity = (TournamentParticipationId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idTournament, entity.idTournament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idTournament);
    }

}