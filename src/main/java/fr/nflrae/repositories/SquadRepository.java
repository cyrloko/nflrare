package fr.nflrae.repositories;

import fr.nflrae.games.Squad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SquadRepository extends JpaRepository<Squad,Integer> {
}
