package com.intuit.chirp.tweets.model.dto;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record TweetResponse(Long id,
                            Long userId,
                            String userFirstName,
                            String userLastName,
                            Timestamp createdAt,
                            String content) {

}
