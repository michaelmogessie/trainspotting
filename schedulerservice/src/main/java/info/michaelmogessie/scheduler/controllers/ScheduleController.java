package info.michaelmogessie.scheduler.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import info.michaelmogessie.scheduler.pojos.Schedule;
import info.michaelmogessie.scheduler.repositories.ScheduleRepository;

@RestController
public class ScheduleController {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping(value = "/schedules")
    public ResponseEntity<CollectionModel<EntityModel<Schedule>>> getSchedules() {
        List<EntityModel<Schedule>> schedules = StreamSupport.stream(scheduleRepository.findAll().spliterator(), false)
                .map(schedule -> EntityModel.of(schedule,
                        linkTo(methodOn(ScheduleController.class).getSchedule(schedule.getScheduleId())).withSelfRel(),
                        linkTo(methodOn(ScheduleController.class).getSchedules()).withRel("schedules")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(schedules, linkTo(methodOn(ScheduleController.class).getSchedules()).withSelfRel()));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<EntityModel<Schedule>> getSchedule(@PathVariable int scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .map(schedule -> EntityModel.of(schedule,
                        linkTo(methodOn(ScheduleController.class).getSchedule(schedule.getScheduleId())).withSelfRel(),
                        linkTo(methodOn(ScheduleController.class).getSchedules()).withRel("schedules")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
