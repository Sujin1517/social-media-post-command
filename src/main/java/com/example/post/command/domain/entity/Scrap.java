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
public class Scrap {
    private Long id;
    private UUID userId;
    private Date createdAt;
}
