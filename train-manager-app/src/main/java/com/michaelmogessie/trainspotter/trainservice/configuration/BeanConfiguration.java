package com.michaelmogessie.trainspotter.trainservice.configuration;

import com.michaelmogessie.trainspotter.trainservice.business.TrainBusiness;
import com.michaelmogessie.trainspotter.trainservice.repository.TrainRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class BeanConfiguration {

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder().region(Region.US_EAST_2).build();
    }

    @Bean
    public TrainBusiness trainBusiness(TrainRepository trainRepository, SnsClient snsClient) {
        return new TrainBusiness(trainRepository, snsClient);
    }

}
