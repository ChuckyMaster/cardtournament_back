package com.mary_tournament.tournament.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "prizes")
public class Prize {
    @Id
    @ColumnDefault("nextval('prizes_id_prize_seq')")
    @Column(name = "id_prize", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tournament", nullable = false)
    private Tournament idTournament;

    @Column(name = "\"position\"", nullable = false)
    private Integer position;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "description", length = 100)
    private String description;

}