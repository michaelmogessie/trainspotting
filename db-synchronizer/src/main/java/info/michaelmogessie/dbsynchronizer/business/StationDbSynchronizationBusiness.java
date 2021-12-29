package info.michaelmogessie.dbsynchronizer.business;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Value;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;

public class StationDbSynchronizationBusiness extends DbSynchronizationBusiness {

    @Value("${sqsStationsQueueUrl}")
    private String sqsStationsQueueUrl;
    @Value("${sqsQueuePollingInterval}")
    private int sqsQueuePollingInterval;

    public StationDbSynchronizationBusiness(ScheduleBusiness scheduleBusiness, SqsClient sqsClient) {
        super(scheduleBusiness, sqsClient);
    }

    @Override
    public void run() {
        while (true) {
            try {
                List<Message> messages = pollSqsQueue(sqsStationsQueueUrl);
                if (!messages.isEmpty()) {
                    scheduleBusiness.updateStations(messages);
                }
                Thread.sleep(sqsQueuePollingInterval);
            } catch (InterruptedException | ExecutionException | JsonProcessingException e) {
                System.err.println(e);
            }
        }
    }

}
