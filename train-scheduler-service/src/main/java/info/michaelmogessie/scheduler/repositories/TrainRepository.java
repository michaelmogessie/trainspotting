package info.michaelmogessie.scheduler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.michaelmogessie.scheduler.pojos.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {

}
