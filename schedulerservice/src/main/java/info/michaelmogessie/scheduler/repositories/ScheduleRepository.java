package info.michaelmogessie.scheduler.repositories;

import org.springframework.data.repository.CrudRepository;

import info.michaelmogessie.scheduler.pojos.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {

}
