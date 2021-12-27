package info.michaelmogessie.dbsynchronizer.business;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

public class DbSynchronizerBusiness implements Runnable {
    private ScheduleBusiness scheduleBusiness;

    @Value("${sqsTrainsQueueUrl}")
    private String sqsTrainsQueueUrl;
    @Autowired
    private SqsAsyncClient sqsAsyncClient;

    public DbSynchronizerBusiness(ScheduleBusiness scheduleBusiness) {
        this.scheduleBusiness = scheduleBusiness;
    }

    @PostConstruct
    public void startPollingSqsQueue() {
        Thread pollingThread = new Thread(this);
        pollingThread.start();
    }

    public List<Message> pollSqsTrainsQueue() throws InterruptedException, ExecutionException {

        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(sqsTrainsQueueUrl)
                .maxNumberOfMessages(5)
                .build();
        return sqsAsyncClient.receiveMessage(receiveMessageRequest).get().messages();
    }

    @Override
    public void run() {
        System.out.println("Polling thread has started running.");
        while (true) {
            System.out.println("Waking up.");
            try {
                try {
                    List<Message> messages = pollSqsTrainsQueue();
                    if (!messages.isEmpty()) {
                        scheduleBusiness.updateTrains(messages);
                    }
                } catch (InterruptedException | ExecutionException | JsonProcessingException e) {
                    System.err.println(e);
                }
                System.out.println("Going to sleep.");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}
