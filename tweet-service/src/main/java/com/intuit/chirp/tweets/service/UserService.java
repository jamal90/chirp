package com.intuit.chirp.tweets.service;

import com.intuit.chirp.tweets.exception.UserNotFoundException;
import com.intuit.chirp.tweets.model.domain.User;
import com.intuit.chirp.tweets.model.dto.UserFollowing;
import com.intuit.chirp.tweets.model.dto.UserResponse;
import com.intuit.chirp.tweets.model.mapper.UserMapper;
import com.intuit.chirp.tweets.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Cacheable(value = "userCache")
    public User getUserByLdapId(String ldapId) {
        log.debug("fetching user info from DB");
        return userRepository.findByLdapId(ldapId);
    }

    @Cacheable(value = "userCache")
    public User getUserById(long id) {
        log.debug("fetching user info from DB");
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user does not exist"));
    }

    public boolean isValidUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(u -> true).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    public List<UserResponse> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userMapper::toUserResponse)
                .toList();
    }

    public List<UserFollowing> getUsersWithFollowing(long id) {
        return userRepository.userWithFollowingStatus(id);
    }
}
