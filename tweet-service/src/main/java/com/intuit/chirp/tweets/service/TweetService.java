package com.intuit.chirp.tweets.service;

import com.intuit.chirp.tweets.model.domain.Tweet;
import com.intuit.chirp.tweets.model.domain.User;
import com.intuit.chirp.tweets.model.dto.TweetRequest;
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
    private final TweetMapper tweetMapper;
    public TweetResponse save(TweetRequest tweetRequest, Jwt principal) {
        Tweet tweet = tweetMapper.toTweet(tweetRequest);
        incTweetMetrics();

        User user = userService.getUserByLdapId(getUserName(principal));
        tweet.setUserId(user.getId());
        tweet.setCreatedAt(Timestamp.from(Instant.now()));
        Tweet savedTweet = tweetRepository.save(tweet);

        return tweetMapper.toTweetResponseBuilder(savedTweet)
                .userFirstName(user.getFirstName())
                .userLastName(user.getLastName())
                .build();

    }

    private void incTweetMetrics() {
        Counter counter = Counter.builder("tweets_total")
//                .tag("<name>", <tag_value>) TODO - can add tags later
                .description("number of tweets posted")
                .register(meterRegistry);

        counter.increment();
    }

    public List<TweetResponse> getTweetsByUser(Jwt userPrincipal) {
        User user = userService.getUserByLdapId(getUserName(userPrincipal));
        return tweetRepository.getAllByUserIdOrderByCreatedAtDesc(user.getId())
                .stream()
                .map(tweetMapper::toTweetResponseBuilder)
                .map(tweetResponseBuilder -> tweetResponseBuilder.userFirstName(user.getFirstName())
                        .userLastName(user.getLastName()).build())
                .toList();
    }
}
