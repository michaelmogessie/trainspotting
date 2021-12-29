package info.michaelmogessie.scheduler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.michaelmogessie.scheduler.pojos.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
}
