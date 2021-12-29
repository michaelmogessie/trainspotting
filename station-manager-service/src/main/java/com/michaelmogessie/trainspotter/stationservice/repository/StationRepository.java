package com.michaelmogessie.trainspotter.stationservice.repository;

import com.michaelmogessie.trainspotter.stationservice.pojos.Station;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Integer> {

}
