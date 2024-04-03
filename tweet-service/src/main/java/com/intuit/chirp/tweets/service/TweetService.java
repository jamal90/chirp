package com.intuit.chirp.tweets.service;

import com.intuit.chirp.tweets.model.domain.Tweet;
import com.intuit.chirp.tweets.model.domain.User;
import com.intuit.chirp.tweets.model.dto.TweetResponse;
import com.intuit.chirp.tweets.model.mapper.TweetMapper;
import com.intuit.chirp.tweets.repository.TweetRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static com.intuit.chirp.tweets.utils.SecurityUtils.getUserName;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepository;
    private final UserService userService;
    private final MeterRegistry meterRegistry;
    public Tweet save(Tweet tweet, Jwt principal) {
        incTweetMetrics();

        User user = userService.getUserByLdapId(getUserName(principal));
        tweet.setUserId(user.getId());
        tweet.setCreatedAt(Timestamp.from(Instant.now()));
        return tweetRepository.save(tweet);
    }

    private void incTweetMetrics() {
        Counter counter = Counter.builder("tweets_total")
//                .tag("<name>", <tag_value>) TODO - can add tags later
                .description("number of tweets posted")
                .register(meterRegistry);

        counter.increment();
    }

    public List<Tweet> getTweetsByUser(Jwt userPrincipal) {
        User user = userService.getUserByLdapId(getUserName(userPrincipal));
        return tweetRepository.getAllByUserId(user.getId());
    }
}
