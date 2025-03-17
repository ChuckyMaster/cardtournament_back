package com.mary_tournament.tournament.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentParticipationDTO {
    private Long userId;
    private Long tournamentId;
}
