package com.michaelmogessie.trainspotter.stationservice.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.michaelmogessie.trainspotter.stationservice.pojos.Station;
import com.michaelmogessie.trainspotter.stationservice.repository.StationRepository;

import org.springframework.beans.factory.annotation.Value;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

public class StationBusiness {

    private final StationRepository stationRepository;
    private final SnsClient snsClient;
    @Value("${sns.topicarn.stations}")
    private String stationsTopicArn;

    public StationBusiness(StationRepository stationRepository, SnsClient snsClient) {
        this.stationRepository = stationRepository;
        this.snsClient = snsClient;
    }

    public Station saveStation(Station station) {
        station = stationRepository.save(station);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String message = mapper.writeValueAsString(station);
            PublishRequest request = PublishRequest.builder().message(message).topicArn(stationsTopicArn)
                    .messageGroupId("stations").build();
            PublishResponse result = snsClient.publish(request);
            System.out
                    .println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());
        } catch (SnsException | JsonProcessingException e) {
            System.err.println(e);
        }
        return station;
    }

    public void removeStation(Integer stationId) {
        stationRepository.deleteById(stationId);
        try {
            PublishRequest request = PublishRequest.builder().message(String.valueOf(stationId))
                    .topicArn(stationsTopicArn)
                    .messageGroupId("stations").build();
            PublishResponse result = snsClient.publish(request);
            System.out
                    .println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());
        } catch (SnsException e) {
            System.err.println(e);
        }
    }

}
