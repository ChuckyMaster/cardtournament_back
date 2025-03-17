package com.mary_tournament.tournament.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class TournamentDTO {

    private Long id;

    private String name;

    private LocalDate dateStart;

    private LocalDate dateEnd;

    private Integer maxPlayers;

    private Integer price;
}
