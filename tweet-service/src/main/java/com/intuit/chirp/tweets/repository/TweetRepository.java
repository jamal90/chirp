package com.intuit.chirp.tweets.repository;

import com.intuit.chirp.tweets.model.domain.Tweet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TweetRepository extends CrudRepository<com.intuit.chirp.tweets.model.domain.Tweet, Long> {
    List<Tweet> getAllByUserId(long userId);
}
