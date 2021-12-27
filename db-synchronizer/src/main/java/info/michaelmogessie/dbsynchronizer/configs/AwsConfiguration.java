package info.michaelmogessie.dbsynchronizer.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import info.michaelmogessie.dbsynchronizer.business.DbSynchronizerBusiness;
import info.michaelmogessie.dbsynchronizer.business.ScheduleBusiness;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class AwsConfiguration {
    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder().region(Region.US_EAST_2).build();
    }

    @Bean
    public SqsAsyncClient sqslient() {
        return SqsAsyncClient.builder().region(Region.US_EAST_2).build();
    }

    @Bean
    public DbSynchronizerBusiness dbSynchronizerBusiness(ScheduleBusiness scheduleBusiness) {
        return new DbSynchronizerBusiness(scheduleBusiness);
    }
}
