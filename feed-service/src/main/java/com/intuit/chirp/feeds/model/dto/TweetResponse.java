package com.intuit.chirp.feeds.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record TweetResponse(
        @NotNull
        Long id,
        @NotNull
        Long userId,
        String firstName,
        String lastName,
        @NotNull
        String content,
        @NotNull
        Timestamp createdAt) {

}

