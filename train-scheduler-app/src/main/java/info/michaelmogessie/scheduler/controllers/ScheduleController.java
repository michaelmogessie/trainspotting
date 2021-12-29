package info.michaelmogessie.scheduler.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import info.michaelmogessie.scheduler.businesses.ScheduleBusiness;
import info.michaelmogessie.scheduler.pojos.Schedule;
import info.michaelmogessie.scheduler.pojos.Station;
import info.michaelmogessie.scheduler.pojos.Train;
import info.michaelmogessie.scheduler.repositories.ScheduleRepository;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin()
public class ScheduleController {
        @Autowired
        private ScheduleRepository scheduleRepository;
        @Autowired
        private ScheduleBusiness scheduleBusiness;
        @Autowired
        private WebClient.Builder webClientBuilder;

        @GetMapping(value = "/schedules")
        public ResponseEntity<CollectionModel<EntityModel<Schedule>>> getSchedules() {
                List<EntityModel<Schedule>> schedules = StreamSupport
                                .stream(scheduleRepository.findAll().spliterator(), false)
                                .map(schedule -> EntityModel.of(schedule,
                                                linkTo(methodOn(ScheduleController.class)
                                                                .getSchedule(schedule.getScheduleId())).withSelfRel(),
                                                linkTo(methodOn(ScheduleController.class).getSchedules())
                                                                .withRel("schedules")))
                                .collect(Collectors.toList());

                return ResponseEntity.ok(
                                CollectionModel.of(schedules, linkTo(methodOn(ScheduleController.class).getSchedules())
                                                .withSelfRel()));
        }

        @GetMapping("/schedules/{scheduleId}")
        public ResponseEntity<EntityModel<Schedule>> getSchedule(@PathVariable int scheduleId) {
                return scheduleRepository.findById(scheduleId)
                                .map(schedule -> EntityModel.of(schedule,
                                                linkTo(methodOn(ScheduleController.class)
                                                                .getSchedule(schedule.getScheduleId())).withSelfRel(),
                                                linkTo(methodOn(ScheduleController.class).getSchedules())
                                                                .withRel("schedules")))
                                .map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
        }

        @GetMapping(value = "/trains")
        public Mono<CollectionModel<EntityModel<Train>>> getTrains() {
                ParameterizedTypeReference<CollectionModel<EntityModel<Train>>> type = new ParameterizedTypeReference<CollectionModel<EntityModel<Train>>>() {
                };

                return webClientBuilder.build().get()
                                .uri("http://train-management-service/trains")
                                .retrieve().bodyToMono(type);
        }

        @GetMapping(value = "/stations")
        public Mono<CollectionModel<EntityModel<Station>>> getStations() {
                ParameterizedTypeReference<CollectionModel<EntityModel<Station>>> type = new ParameterizedTypeReference<CollectionModel<EntityModel<Station>>>() {
                };
                return webClientBuilder.build().get()
                                .uri("http://station-management-service/stations")
                                .retrieve().bodyToMono(type);
        }

        @PostMapping("/schedules")
        ResponseEntity<?> addSchedule(@RequestBody Schedule schedule) {

                try {
                        schedule = scheduleBusiness.saveSchedule(schedule);

                        EntityModel<Schedule> scheduleModel = EntityModel.of(schedule,
                                        linkTo(methodOn(ScheduleController.class).getSchedule(schedule.getScheduleId()))
                                                        .withSelfRel());

                        return ResponseEntity
                                        .created(new URI(
                                                        scheduleModel.getRequiredLink(IanaLinkRelations.SELF)
                                                                        .getHref()))
                                        .body(scheduleModel);
                } catch (URISyntaxException e) {
                        return ResponseEntity.badRequest().body("Unable to create " + schedule);
                }
        }

}
