package com.mary_tournament.tournament.repository;

import com.mary_tournament.tournament.model.TournamentParticipation;
import com.mary_tournament.tournament.model.TournamentParticipationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentParticipationRepository extends JpaRepository<TournamentParticipation, TournamentParticipationId> {

    @Query("SELECT p FROM TournamentParticipation p WHERE p.idUser.id = :userId AND p.idTournament.id = :tournamentId")
    Optional<TournamentParticipation> findByUserIdAndTournamentId(@Param("userId") Long userId, @Param("tournamentId") Long tournamentId);

}

