package com.mary_tournament.tournament.service;

import com.mary_tournament.tournament.dto.TournamentDTO;
import com.mary_tournament.tournament.dto.TournamentCreateDTO;
import com.mary_tournament.tournament.dto.TournamentResponseDTO;
import com.mary_tournament.tournament.model.CategoryGame;
import com.mary_tournament.tournament.model.Tournament;
import com.mary_tournament.tournament.model.TournamentParticipation;
import com.mary_tournament.tournament.repository.CategoryRepository;
import com.mary_tournament.tournament.repository.TournamentParticipationRepository;
import com.mary_tournament.tournament.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final CategoryRepository categoryRepository;
    private final TournamentParticipationRepository tournamentParticipationRepository;

    private TournamentResponseDTO convertToResponseDTO(Tournament tournament) {
        TournamentResponseDTO tournamentResponseDTO = new TournamentResponseDTO();
        tournamentResponseDTO.setId(tournament.getId());
        tournamentResponseDTO.setName(tournament.getName());
        tournamentResponseDTO.setDateStart(tournament.getDatestart());
        tournamentResponseDTO.setDateEnd(tournament.getDateend());
        tournamentResponseDTO.setPrice(tournament.getPrice());
        tournamentResponseDTO.setMaxPlayers(tournament.getMaxPlayers());
        return tournamentResponseDTO;
    }


    private TournamentDTO convertToDTO(Tournament tournament) {
        TournamentDTO tournamentDTO = new TournamentDTO();
        tournamentDTO.setId(tournament.getId());
        tournamentDTO.setName(tournament.getName());
        tournamentDTO.setDateStart(tournament.getDatestart());
        tournamentDTO.setDateEnd(tournament.getDateend());
        tournamentDTO.setMaxPlayers(tournament.getMaxPlayers());
        return tournamentDTO;
    }

    @Override
    public List<TournamentDTO> getAllTournaments() {
        return tournamentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TournamentResponseDTO> getTournamentById(Long id) {
        return tournamentRepository.findById(id).map(this::convertToResponseDTO);  // Renvoie TournamentResponseDTO détaillé
    }

    @Override
    public TournamentResponseDTO createTournament(TournamentCreateDTO tournamentCreateDTO) {
        Tournament tournament = new Tournament();
        tournament.setName(tournamentCreateDTO.getName());
        tournament.setDatestart(tournamentCreateDTO.getDateStart());
        tournament.setDateend(tournamentCreateDTO.getDateEnd());
        tournament.setMaxPlayers(tournamentCreateDTO.getMaxPlayers());
        tournament.setPrice(tournamentCreateDTO.getPrice());
        tournament.setRegistrationDeadline(tournamentCreateDTO.getRegistrationDeadline());

        CategoryGame category = categoryRepository.findById(tournamentCreateDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée avec l'ID : " + tournamentCreateDTO.getCategoryId()));

        tournament.setIdCategory(category); //

        tournament = tournamentRepository.save(tournament);
        return convertToResponseDTO(tournament);

    }

    @Override
    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);  // Supprimer un tournoi par ID
    }

    @Override
    public TournamentResponseDTO updateTournament(Long id, TournamentCreateDTO tournamentCreateDTO) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        tournament.setName(tournamentCreateDTO.getName());
        tournament.setDatestart(tournamentCreateDTO.getDateStart());
        tournament.setDateend(tournamentCreateDTO.getDateEnd());
        tournament.setMaxPlayers(tournamentCreateDTO.getMaxPlayers());
        tournament.setPrice(tournamentCreateDTO.getPrice());
        tournament.setRegistrationDeadline(tournamentCreateDTO.getRegistrationDeadline());

        tournament = tournamentRepository.save(tournament);

        return convertToResponseDTO(tournament);
    }

    public List<TournamentDTO> getTournamentsByUser(Long userId) {
        List<Tournament> tournaments = tournamentRepository.findTournamentsByUserId(userId);
        return tournaments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void unregisterFromTournament(Long userId, Long tournamentId) {
        TournamentParticipation participation = tournamentParticipationRepository
                .findByUserIdAndTournamentId(userId, tournamentId)
                .orElseThrow(() -> new RuntimeException("L'utilisateur n'est pas inscrit à ce tournoi"));

        tournamentParticipationRepository.delete(participation);
    }



}
