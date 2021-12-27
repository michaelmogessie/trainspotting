package info.michaelmogessie.scheduler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import info.michaelmogessie.scheduler.pojos.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
