package com.michaelmogessie.trainspotter.stationservice.configuration;

import com.michaelmogessie.trainspotter.stationservice.business.StationBusiness;
import com.michaelmogessie.trainspotter.stationservice.repository.StationRepository;

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
    public StationBusiness trainBusiness(StationRepository stationRepository, SnsClient snsCleint) {
        return new StationBusiness(stationRepository, snsCleint);
    }


}
