package com.mary_tournament.tournament.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @ColumnDefault("nextval('payments_id_payment_seq')")
    @Column(name = "id_payment", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tournament", nullable = false)
    private Tournament idTournament;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @ColumnDefault("now()")
    @Column(name = "payment_date")
    private Instant paymentDate;

    @Column(name = "payment_method", nullable = false, length = 50)
    private String paymentMethod;

    @Column(name = "status", length = 20)
    private String status;

}