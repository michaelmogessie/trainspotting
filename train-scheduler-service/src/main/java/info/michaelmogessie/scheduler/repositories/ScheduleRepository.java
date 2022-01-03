package info.michaelmogessie.scheduler.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import info.michaelmogessie.scheduler.pojos.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    public List<Schedule> findByCheckPointsStationStationIdEqualsOrderByCheckPointsEtaAsc(Integer stationId);
}
