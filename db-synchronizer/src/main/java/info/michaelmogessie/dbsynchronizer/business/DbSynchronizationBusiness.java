package info.michaelmogessie.dbsynchronizer.business;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

public abstract class DbSynchronizationBusiness implements Runnable {
    final SqsAsyncClient sqsAsyncClient;
    final ScheduleBusiness scheduleBusiness;

    DbSynchronizationBusiness(ScheduleBusiness scheduleBusiness, SqsAsyncClient sqsAsyncClient) {
        this.scheduleBusiness = scheduleBusiness;
        this.sqsAsyncClient = sqsAsyncClient;
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
        List<Message> messages = sqsAsyncClient.receiveMessage(receiveMessageRequest).get().messages();
        for (Message message : messages) {
            DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                    .queueUrl(sqsQueueUrl)
                    .receiptHandle(message.receiptHandle())
                    .build();
            sqsAsyncClient.deleteMessage(deleteMessageRequest);
        }
        return messages;
    }
}
