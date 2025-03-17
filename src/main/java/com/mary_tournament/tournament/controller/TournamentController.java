package com.mary_tournament.tournament.controller;


import com.mary_tournament.tournament.dto.TournamentCreateDTO;
import com.mary_tournament.tournament.dto.TournamentResponseDTO;
import com.mary_tournament.tournament.dto.TournamentDTO;
import com.mary_tournament.tournament.dto.TournamentParticipationDTO;
import com.mary_tournament.tournament.model.Tournament;
import com.mary_tournament.tournament.model.TournamentParticipation;
import com.mary_tournament.tournament.model.TournamentParticipationId;
import com.mary_tournament.tournament.model.User;
import com.mary_tournament.tournament.repository.TournamentRepository;
import com.mary_tournament.tournament.repository.UserRepository;
import com.mary_tournament.tournament.repository.TournamentParticipationRepository;
import com.mary_tournament.tournament.service.TournamentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tournaments")
@RequiredArgsConstructor
@Tag(name = "Tournament", description = "API pour gérer les tournois")
public class TournamentController {

    private final TournamentService tournamentService;
    private final UserRepository userRepository;
    private final TournamentRepository tournamentRepository;
    private final TournamentParticipationRepository tournamentParticipationRepository; // ✅ Injection correcte

    @GetMapping
    public ResponseEntity<List<TournamentDTO>> getAllTournaments() {
        List<TournamentDTO> tournaments = tournamentService.getAllTournaments();
        return ResponseEntity.ok(tournaments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentResponseDTO> getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TournamentResponseDTO> createTournament(@RequestBody TournamentCreateDTO tournamentCreateDTO) {
        TournamentResponseDTO createdTournament = tournamentService.createTournament(tournamentCreateDTO);
        return ResponseEntity.status(201).body(createdTournament);
    }

    @DeleteMapping("/tournaments/{id}")
    public ResponseEntity<?> deleteTournament(@PathVariable Long id) {
        try {
            tournamentService.deleteTournament(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace(); //
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du tournoi");
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TournamentDTO>> getTournamentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(tournamentService.getTournamentsByUser(userId));
    }



    // Mettre à jour un tournoi
    @PutMapping("/{id}")
    public ResponseEntity<TournamentResponseDTO> updateTournament(
            @PathVariable Long id,
            @RequestBody TournamentCreateDTO tournamentCreateDTO) {

        TournamentResponseDTO updatedTournament = tournamentService.updateTournament(id, tournamentCreateDTO);
        return ResponseEntity.ok(updatedTournament);  // Retourner le tournoi mis à jour
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUserToTournament(@RequestBody TournamentParticipationDTO participationDTO) {
        Optional<User> userOpt = userRepository.findById(Math.toIntExact(participationDTO.getUserId()));
        Optional<Tournament> tournamentOpt = tournamentRepository.findById(participationDTO.getTournamentId());

        if (userOpt.isEmpty() || tournamentOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Utilisateur ou tournoi introuvable.");
        }
        User user = userOpt.get();
        Tournament tournament = tournamentOpt.get();

        //Vérifier si l'utilisateur est déjà inscrit
        TournamentParticipationId participationId = new TournamentParticipationId();
        participationId.setIdUser(user.getId());
        participationId.setIdTournament(tournament.getId()); //

        if (tournamentParticipationRepository.existsById(participationId)) {
            return ResponseEntity.badRequest().body("❌ L'utilisateur est déjà inscrit à ce tournoi.");
        }

        // Inscription
        TournamentParticipation participation = new TournamentParticipation();
        participation.setId(participationId);
        participation.setIdUser(user);
        participation.setIdTournament(tournament);

        tournamentParticipationRepository.save(participation);

        return ResponseEntity.ok("✅ Inscription au tournoi réussie !");
    }

    @PostMapping("/tournaments/unregister")
    public ResponseEntity<?> unregisterFromTournament(@RequestBody Map<String, Long> requestData) {
        Long userId = requestData.get("userId");
        Long tournamentId = requestData.get("tournamentId");

        tournamentService.unregisterFromTournament(userId, tournamentId);
        return ResponseEntity.ok().body("Désinscription réussie !");
    }


}


