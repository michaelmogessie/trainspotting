package info.michaelmogessie.dbsynchronizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import info.michaelmogessie.dbsynchronizer.pojos.Station;

public interface StationRepository extends JpaRepository<Station, Integer> {

}
