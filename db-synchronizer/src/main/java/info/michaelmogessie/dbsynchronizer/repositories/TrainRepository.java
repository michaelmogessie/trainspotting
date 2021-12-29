package info.michaelmogessie.dbsynchronizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import info.michaelmogessie.dbsynchronizer.pojos.Train;

public interface TrainRepository extends JpaRepository<Train, Integer> {
    
}
