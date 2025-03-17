package com.mary_tournament.tournament.repository;

import com.mary_tournament.tournament.model.CategoryGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryGame, Long> {
}
