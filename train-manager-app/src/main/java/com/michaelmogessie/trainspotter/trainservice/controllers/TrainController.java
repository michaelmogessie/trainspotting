package com.michaelmogessie.trainspotter.trainservice.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.michaelmogessie.trainspotter.trainservice.pojos.Train;
import com.michaelmogessie.trainspotter.trainservice.repositories.TrainRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@CrossOrigin()
public class TrainController {
        @Autowired
        private TrainRepository trainRepository;

        @GetMapping(value = "/trains")
        public ResponseEntity<CollectionModel<EntityModel<Train>>> getTrains() {
                List<EntityModel<Train>> trains = StreamSupport.stream(trainRepository.findAll().spliterator(), false)
                                .map(train -> EntityModel.of(train,
                                                linkTo(methodOn(TrainController.class).getTrain(train.getTrainId()))
                                                                .withSelfRel(),
                                                linkTo(methodOn(TrainController.class).getTrains()).withRel("trains")))
                                .collect(Collectors.toList());

                return ResponseEntity.ok(
                                CollectionModel.of(trains,
                                                linkTo(methodOn(TrainController.class).getTrains()).withSelfRel()));
        }

        @GetMapping("/trains/{trainId}")
        public ResponseEntity<EntityModel<Train>> getTrain(@PathVariable int trainId) {
                return trainRepository.findById(trainId)
                                .map(train -> EntityModel.of(train,
                                                linkTo(methodOn(TrainController.class).getTrain(train.getTrainId()))
                                                                .withSelfRel(),
                                                linkTo(methodOn(TrainController.class).getTrains()).withRel("trains")))
                                .map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping("/trains")
        ResponseEntity<?> addTrain(@RequestBody Train train) {

                try {
                        train = trainRepository.save(train);

                        EntityModel<Train> trainModel = EntityModel.of(train,
                                        linkTo(methodOn(TrainController.class).getTrain(train.getTrainId()))
                                                        .withSelfRel());

                        return ResponseEntity
                                        .created(new URI(trainModel.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                                        .body(trainModel);
                } catch (URISyntaxException e) {
                        return ResponseEntity.badRequest().body("Unable to create " + train);
                }
        }

}
