package info.michaelmogessie.dbsynchronizer.business;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Value;

import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.Message;

public class StationDbSynchronizationBusiness extends DbSynchronizationBusiness {

    @Value("${sqsStationsQueueUrl}")
    private String sqsStationsQueueUrl;

    public StationDbSynchronizationBusiness(ScheduleBusiness scheduleBusiness, SqsAsyncClient sqsAsyncClient) {
        super(scheduleBusiness, sqsAsyncClient);
    }

    @Override
    public void run() {
        while (true) {
            try {
                List<Message> messages = pollSqsQueue(sqsStationsQueueUrl);
                if (!messages.isEmpty()) {
                    scheduleBusiness.updateStations(messages);
                }
                Thread.sleep(5000);
            } catch (InterruptedException | ExecutionException | JsonProcessingException e) {
                System.err.println(e);
            }
        }
    }

}
