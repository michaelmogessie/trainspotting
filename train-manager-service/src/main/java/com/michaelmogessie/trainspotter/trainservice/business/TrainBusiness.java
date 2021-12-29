package com.michaelmogessie.trainspotter.trainservice.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.michaelmogessie.trainspotter.trainservice.pojos.Train;
import com.michaelmogessie.trainspotter.trainservice.repository.TrainRepository;

import org.springframework.beans.factory.annotation.Value;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

public class TrainBusiness {
    private final TrainRepository trainRepository;
    private final SnsClient snsClient;
    @Value("${sns.topicarn.trains}")
    private String trainsTopicArn;

    public TrainBusiness(TrainRepository trainRepository, SnsClient snsClient) {
        this.trainRepository = trainRepository;
        this.snsClient = snsClient;
    }

    public Train saveTrain(Train train) {
        train = trainRepository.save(train);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String message = mapper.writeValueAsString(train);
            PublishRequest request = PublishRequest.builder().message(message).topicArn(trainsTopicArn)
                    .messageGroupId("trains").build();
            PublishResponse result = snsClient.publish(request);
            System.out
                    .println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());
        } catch (SnsException | JsonProcessingException e) {
            System.err.println(e);
        }
        return train;
    }
}
