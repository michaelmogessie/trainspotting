package info.michaelmogessie.dbsynchronizer.business;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Value;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;

public class TrainDbSynchronizationBusiness extends DbSynchronizationBusiness {

    @Value("${sqsTrainsQueueUrl}")
    private String sqsTrainsQueueUrl;
    @Value("${sqsQueuePollingInterval}")
    private int sqsQueuePollingInterval;

    public TrainDbSynchronizationBusiness(ScheduleBusiness scheduleBusiness, SqsClient sqsClient) {
        super(scheduleBusiness, sqsClient);
    }

    @Override
    public void run() {
        while (true) {
            try {
                List<Message> messages = pollSqsQueue(sqsTrainsQueueUrl);
                if (!messages.isEmpty()) {
                    scheduleBusiness.updateTrains(messages);
                }
                Thread.sleep(sqsQueuePollingInterval);
            } catch (InterruptedException | ExecutionException | JsonProcessingException e) {
                System.err.println(e);
            }
        }
    }
}
