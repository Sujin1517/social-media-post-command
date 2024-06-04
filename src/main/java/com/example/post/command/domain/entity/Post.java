package com.example.post.command.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
public class Post {
    private Long id;
    private UUID userId;
    private String content;
    private Long likeNum;
    private Long scrapNum;
    private Date createdAt;
    private String file;
    private String fileType;
    private Boolean isDisable;
}
