package com.example.post.command.controller;

import com.example.post.command.domain.dto.KafkaStatus;
import com.example.post.command.domain.entity.Like;
import com.example.post.command.domain.entity.Post;
import com.example.post.command.domain.entity.PostChain;
import com.example.post.command.kafka.producer.PostProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostProducer postProducer;
    private final List<KafkaStatus<Post>> kafkaList = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody Post req) {
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Long id){
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createChain(@RequestBody PostChain req) {
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createLike(@RequestBody Like req) {
    }
}
