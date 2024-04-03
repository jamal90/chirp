package com.intuit.chirp.tweets.controller;

import com.intuit.chirp.tweets.model.domain.Tweet;
import com.intuit.chirp.tweets.model.dto.TweetRequest;
import com.intuit.chirp.tweets.model.dto.TweetResponse;
import com.intuit.chirp.tweets.model.mapper.TweetMapper;
import com.intuit.chirp.tweets.service.TweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.intuit.chirp.tweets.utils.SecurityUtils.getUserName;

@RestController
@RequestMapping("/api/v1/tweets")
@RequiredArgsConstructor
public class TweetController {

    private final TweetMapper tweetMapper;
    private final TweetService tweetService;

    @PostMapping
    public TweetResponse save(@Valid @RequestBody TweetRequest tweetRequest, @AuthenticationPrincipal Jwt principal) {
        Tweet savedTweet = tweetService.save(tweetMapper.toTweet(tweetRequest), principal);
        return tweetMapper.toTweetResponse(savedTweet);
    }

    @GetMapping
    public List<TweetResponse> getAllTweets(@AuthenticationPrincipal Jwt principal) {
        return tweetService.getTweetsByUser(principal)
                .stream().map(tweetMapper::toTweetResponse)
                .toList();
    }
}
