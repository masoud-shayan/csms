package com.chargepoint.csms.authenticationservice.message.processor;

import com.chargepoint.csms.authenticationservice.service.AuthorizationService;
import com.chargepoint.csms.common.type.message.base.AuthorizationResponseMessage;
import com.chargepoint.csms.common.type.message.base.EventMessage;
import com.chargepoint.csms.common.type.message.payload.AuthorizationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;

@Configuration
@Slf4j
@AllArgsConstructor
public class ConsumerProcessor {

    private final AuthorizationService authorizationService;

    @KafkaListener(id = "driver-auth-service", topics = "${kafka.topic.request}")
    @SendTo
    public EventMessage<?> listen(EventMessage<?> in) {

        log.info("Consumer received: {}" , in);

        return AuthorizationResponseMessage.builder()
                .body(authorizationService.authorizeDriverAndStation((AuthorizationRequest) in.getBody()))
                .build();
    }
}
