package com.intuit.chirp.tweets.model.dto;

public record UserResponse (
        long id,
        String firstName,
        String lastName,
        int followers,
        int following
//        boolean userFollows
) {

}
