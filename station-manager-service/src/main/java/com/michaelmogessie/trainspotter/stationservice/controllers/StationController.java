package com.michaelmogessie.trainspotter.stationservice.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.michaelmogessie.trainspotter.stationservice.business.StationBusiness;
import com.michaelmogessie.trainspotter.stationservice.pojos.Station;
import com.michaelmogessie.trainspotter.stationservice.repository.StationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class StationController {
        @Autowired
        private StationRepository stationRepository;
        @Autowired
        StationBusiness stationBusiness;

        @GetMapping(value = "/stations")
        public ResponseEntity<CollectionModel<EntityModel<Station>>> getStations() {
                List<EntityModel<Station>> stations = StreamSupport
                                .stream(stationRepository.findAll().spliterator(), false)
                                .map(station -> EntityModel.of(station,
                                                linkTo(methodOn(StationController.class)
                                                                .getStation(station.getStationId())).withSelfRel(),
                                                linkTo(methodOn(StationController.class).getStations())
                                                                .withRel("stations")))
                                .collect(Collectors.toList());

                return ResponseEntity.ok(
                                CollectionModel.of(stations,
                                                linkTo(methodOn(StationController.class).getStations()).withSelfRel()));
        }

        @GetMapping("/stations/{stationId}")
        public ResponseEntity<EntityModel<Station>> getStation(@PathVariable int stationId) {
                return stationRepository.findById(stationId)
                                .map(station -> EntityModel.of(station,
                                                linkTo(methodOn(StationController.class)
                                                                .getStation(station.getStationId())).withSelfRel(),
                                                linkTo(methodOn(StationController.class).getStations())
                                                                .withRel("stations")))
                                .map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping("/stations")
        ResponseEntity<?> addStation(@RequestBody Station station) {

                try {
                        station = stationBusiness.saveStation(station);

                        EntityModel<Station> stationModel = EntityModel.of(station,
                                        linkTo(methodOn(StationController.class).getStation(station.getStationId()))
                                                        .withSelfRel());

                        return ResponseEntity
                                        .created(new URI(
                                                        stationModel.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                                        .body(stationModel);
                } catch (URISyntaxException e) {
                        return ResponseEntity.badRequest().body("Unable to create " + station);
                }
        }

        @DeleteMapping("/stations/{stationId}")
        ResponseEntity<?> removeStation(@PathVariable int stationId) {

                try {
                        stationBusiness.removeStation(stationId);
                        return ResponseEntity.ok().build();
                } catch (Exception e) {
                        return ResponseEntity.badRequest().body("Unable to delete " + stationId);
                }
        }
}
