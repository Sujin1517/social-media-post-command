package com.example.post.command.controller;

import com.example.post.command.domain.dto.KafkaStatus;
import com.example.post.command.domain.entity.Like;
import com.example.post.command.domain.entity.Post;
import com.example.post.command.domain.entity.PostChain;
import com.example.post.command.kafka.producer.PostProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostProducer postProducer;
    private final List<KafkaStatus<Post>> kafkaList = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody Post req) {
        KafkaStatus<Post> userKafkaStatus = new KafkaStatus<>(req, "insertPost");
        kafkaList.add(userKafkaStatus);
        postProducer.send(req, "insertPost");
        kafkaList.remove(userKafkaStatus);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Long id){
        Post post = Post.builder().id(id).build();
        KafkaStatus<Post> userKafkaStatus = new KafkaStatus<>(post, "deletePost");
        kafkaList.add(userKafkaStatus);
        postProducer.send(post, "deletePost");
        kafkaList.remove(userKafkaStatus);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createChain(@RequestBody PostChain req) {
//
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createLike(@RequestBody Like req) {
//
//    }

    @Scheduled(cron ="*/10 * * * * *")
    public void reSend() {
        List<KafkaStatus<Post>> sucessList = new ArrayList<>();
        kafkaList.forEach(e -> {
            postProducer.send(e.data(), e.status());
            sucessList.add(e);
        });
        sucessList.forEach(kafkaList::remove);
    }
}
