package info.michaelmogessie.dbsynchronizer.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import info.michaelmogessie.dbsynchronizer.business.DbSynchronizationBusiness;
import info.michaelmogessie.dbsynchronizer.business.ScheduleBusiness;
import info.michaelmogessie.dbsynchronizer.business.StationDbSynchronizationBusiness;
import info.michaelmogessie.dbsynchronizer.business.TrainDbSynchronizationBusiness;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class AwsConfiguration {
    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder().region(Region.US_EAST_2).build();
    }

    @Bean
    public DbSynchronizationBusiness trainDbSynchronizerBusiness(ScheduleBusiness scheduleBusiness,
            SqsAsyncClient sqsAsyncClient) {
        return new TrainDbSynchronizationBusiness(scheduleBusiness, sqsAsyncClient);
    }

    @Bean
    public DbSynchronizationBusiness stationDbSynchronizerBusiness(ScheduleBusiness scheduleBusiness,
            SqsAsyncClient sqsAsyncClient) {
        return new StationDbSynchronizationBusiness(scheduleBusiness, sqsAsyncClient);
    }
}
