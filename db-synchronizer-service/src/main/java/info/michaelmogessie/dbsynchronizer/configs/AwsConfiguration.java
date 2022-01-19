package info.michaelmogessie.dbsynchronizer.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import info.michaelmogessie.dbsynchronizer.business.DbSynchronizationBusiness;
import info.michaelmogessie.dbsynchronizer.business.ScheduleBusiness;
import info.michaelmogessie.dbsynchronizer.business.StationDbSynchronizationBusiness;
import info.michaelmogessie.dbsynchronizer.business.TrainDbSynchronizationBusiness;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsConfiguration {
    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder().region(Region.US_EAST_2).build();
    }

    @Bean
    public DbSynchronizationBusiness trainDbSynchronizerBusiness(ScheduleBusiness scheduleBusiness,
            SqsClient sqsClient) {
        return new TrainDbSynchronizationBusiness(scheduleBusiness, sqsClient);
    }

    @Bean
    public DbSynchronizationBusiness stationDbSynchronizerBusiness(ScheduleBusiness scheduleBusiness,
            SqsClient sqsClient) {
        return new StationDbSynchronizationBusiness(scheduleBusiness, sqsClient);
    }
}
