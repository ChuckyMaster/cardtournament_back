package com.mary_tournament.tournament.repository;

import com.mary_tournament.tournament.model.Tournament;
import com.mary_tournament.tournament.model.TournamentParticipation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    @Query("SELECT t FROM Tournament t JOIN TournamentParticipation tp ON t.id = tp.idTournament.id WHERE tp.idUser.id = :userId")
    List<Tournament> findTournamentsByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM TournamentParticipation p WHERE p.idUser.id = :userId AND p.idTournament.id = :tournamentId")
    Optional<TournamentParticipation> findByUserIdAndTournamentId(@Param("userId") Long userId, @Param("tournamentId") Long tournamentId);

//
//    List<Tournament> findByTournamentParticipations_IdUser_Id(Long userId);
//    Optional<TournamentParticipation> findByIdUser_IdAndIdTournament_Id(Long userId, Long tournamentId);
//


}
