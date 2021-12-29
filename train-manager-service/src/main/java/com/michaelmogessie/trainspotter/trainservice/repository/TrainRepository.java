package com.michaelmogessie.trainspotter.trainservice.repository;

import com.michaelmogessie.trainspotter.trainservice.pojos.Train;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {

}
