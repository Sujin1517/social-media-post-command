package com.example.post.command.domain.dto;

public record KafkaStatus<T>(
        T data,
        String status
) {
}
