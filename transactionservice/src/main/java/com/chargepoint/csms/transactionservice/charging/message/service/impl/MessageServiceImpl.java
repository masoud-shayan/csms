package com.chargepoint.csms.transactionservice.charging.message.service.impl;

import com.chargepoint.csms.common.type.message.base.EventMessage;
import com.chargepoint.csms.transactionservice.charging.message.service.RequestReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyMessageFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements RequestReplyService {

    private final ReplyingKafkaTemplate<String, EventMessage<?>, EventMessage<?>> replyingKafkaTemplate;


    @Override
    public EventMessage<?> send(EventMessage<?> eventMessage) {

        EventMessage<?> result = null;

        try {

            if (!replyingKafkaTemplate.waitForAssignment(Duration.ofSeconds(10))) {
                throw new IllegalStateException("Reply container did not initialize");
            }

            RequestReplyMessageFuture<String, EventMessage<?>> replyFuture = replyingKafkaTemplate.sendAndReceive(MessageBuilder.withPayload(eventMessage).build());
            SendResult<String, EventMessage<?>> sendResult = replyFuture.getSendFuture().get(10, TimeUnit.SECONDS);
            log.info("Sent ok: {}", sendResult.getRecordMetadata());
            Message<?> message = replyFuture.get(10, TimeUnit.SECONDS);
            log.info("Return value: {} ", ((EventMessage<?>) message.getPayload()).getBody());
            result = (EventMessage<?>) message.getPayload();


        } catch (TimeoutException e) {
            log.error("Task timed out. Please try again later: {}", e.getCause().toString());
            e.printStackTrace();
        } catch (InterruptedException e) {
            log.error("Thread was interrupted. Cleaning up and stopping the operation: {}", e.getCause().toString());
            e.printStackTrace();
        } catch (ExecutionException e) {
            log.error("Task execution failed: {}", e.getCause().toString());
            e.printStackTrace();
        }

        return result;
    }
}
