package com.example.post.command.kafka.producer;

import com.example.post.command.domain.dto.KafkaStatus;
import com.example.post.command.domain.entity.Post;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostProducer {
    private final KafkaTemplate<String, KafkaStatus<Post>> kafkaTemplate;
    @Value("${kafka.topic.name}") private String topic;

    @Bean
    private NewTopic newTopic() {
        return new NewTopic(topic, 1, (short) 1);
    }

    public void send(Post post, String status) {
        KafkaStatus<Post> kafkaStatus = new KafkaStatus<>(post, status);
        kafkaTemplate.send(topic, kafkaStatus);
    }
}

