package com.chargepoint.csms.transactionservice.charging.message.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "kafka.topic")
public class KafkaTopicProperties {

    private String request;
    private String reply;
    private String replyGroup;
}
