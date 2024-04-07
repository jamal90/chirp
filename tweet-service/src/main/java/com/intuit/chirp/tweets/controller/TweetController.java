package com.intuit.chirp.tweets.controller;

import com.intuit.chirp.tweets.model.dto.TweetRequest;
import com.intuit.chirp.tweets.model.dto.TweetResponse;
import com.intuit.chirp.tweets.service.TweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tweets")
@RequiredArgsConstructor
public class TweetController {

    private final TweetService tweetService;

    @PostMapping
    public TweetResponse save(@Valid @RequestBody TweetRequest tweetRequest, @AuthenticationPrincipal Jwt principal) {
        return tweetService.save(tweetRequest, principal);
    }

    @GetMapping
    public List<TweetResponse> getAllTweets(@AuthenticationPrincipal Jwt principal) {
        return tweetService.getTweetsByUser(principal);
    }
}
