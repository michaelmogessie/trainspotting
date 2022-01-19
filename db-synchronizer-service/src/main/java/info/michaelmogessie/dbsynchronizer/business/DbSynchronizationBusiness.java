package info.michaelmogessie.dbsynchronizer.business;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

public abstract class DbSynchronizationBusiness implements Runnable {
    final SqsClient sqsClient;
    final ScheduleBusiness scheduleBusiness;

    DbSynchronizationBusiness(ScheduleBusiness scheduleBusiness, SqsClient sqsClient) {
        this.scheduleBusiness = scheduleBusiness;
        this.sqsClient = sqsClient;
    }

    @PostConstruct
    private void startPollingSqsQueue() {
        Thread pollingThread = new Thread(this);
        pollingThread.start();
    }

    List<Message> pollSqsQueue(String sqsQueueUrl) throws InterruptedException, ExecutionException {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(sqsQueueUrl)
                .maxNumberOfMessages(5)
                .build();
        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
        for (Message message : messages) {
            DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                    .queueUrl(sqsQueueUrl)
                    .receiptHandle(message.receiptHandle())
                    .build();
            sqsClient.deleteMessage(deleteMessageRequest);
        }
        return messages;
    }
}
