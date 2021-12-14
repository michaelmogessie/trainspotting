package com.michaelmogessie.trainspotter.trainservice.repositories;

import com.michaelmogessie.trainspotter.trainservice.pojos.Train;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Integer> {

}
