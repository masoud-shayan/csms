package com.chargepoint.csms.transactionservice.charging.message.config;

import com.chargepoint.csms.common.type.message.base.EventMessage;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
@RequiredArgsConstructor
public class RequestReplyConfiguration {

    private final KafkaTopicProperties topicProperties;


    @Bean
    public ReplyingKafkaTemplate<String, EventMessage<?>, EventMessage<?>> repliesContainer(ProducerFactory<String, EventMessage<?>> producerFactory, ConcurrentMessageListenerContainer<String, EventMessage<?>> listenerContainer) {

        ReplyingKafkaTemplate<String, EventMessage<?>, EventMessage<?>> replier = new ReplyingKafkaTemplate<>(producerFactory, listenerContainer);
        replier.setDefaultTopic(topicProperties.getRequest());
        return replier;
    }


    @Bean
    ConcurrentMessageListenerContainer<String, EventMessage<?>> listenerContainer(ConcurrentKafkaListenerContainerFactory<String, EventMessage<?>> listenerContainerFactory, KafkaTemplate<String, EventMessage<?>> kafkaTemplate) {

        listenerContainerFactory.setReplyTemplate(kafkaTemplate);
        ConcurrentMessageListenerContainer<String, EventMessage<?>> listenerContainer = listenerContainerFactory.createContainer(topicProperties.getReply());
        listenerContainer.getContainerProperties().setGroupId(topicProperties.getReplyGroup());

        return listenerContainer;
    }

    @Bean
    KafkaTemplate<String, EventMessage<?>> kafkaTemplate(ProducerFactory<String, EventMessage<?>> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public NewTopic requestTopic() {
        return TopicBuilder
                .name(topicProperties.getRequest())
                .partitions(1)
                .replicas(1).build();
    }

    @Bean
    public NewTopic replyTopic() {
        return TopicBuilder
                .name(topicProperties.getReply())
                .partitions(1)
                .replicas(1).build();
    }
}
