package com.mary_tournament.tournament.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class TournamentCreateDTO {

    private String name;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Integer price;
    private Boolean isPaid;
    private Long categoryId;
    private Integer maxPlayers;
    private LocalDate registrationDeadline;
}
