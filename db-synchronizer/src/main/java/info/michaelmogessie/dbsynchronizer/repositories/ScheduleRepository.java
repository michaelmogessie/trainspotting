package info.michaelmogessie.dbsynchronizer.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import info.michaelmogessie.dbsynchronizer.pojos.Schedule;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {
    public List<Schedule> findByTrainTrainIdEquals(Integer trainId);

    public List<Schedule> findByDeparturesStationStationIdEquals(Integer stationId);
    public List<Schedule> findByArrivalsStationStationIdEquals(Integer stationId);
}