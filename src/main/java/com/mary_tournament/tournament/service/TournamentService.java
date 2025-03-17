package com.mary_tournament.tournament.service;

import com.mary_tournament.tournament.dto.TournamentDTO;
import com.mary_tournament.tournament.dto.TournamentCreateDTO;
import com.mary_tournament.tournament.dto.TournamentResponseDTO;

import java.util.List;
import java.util.Optional;

public interface TournamentService {
    List<TournamentDTO> getAllTournaments();
    Optional<TournamentResponseDTO> getTournamentById(Long id);
    TournamentResponseDTO createTournament(TournamentCreateDTO tournamentCreateDTO);
    void deleteTournament(Long id);
    TournamentResponseDTO updateTournament(Long id, TournamentCreateDTO tournamentCreateDTO);
    List<TournamentDTO> getTournamentsByUser(Long userId);
    void unregisterFromTournament(Long userId, Long tournamentId);
}

