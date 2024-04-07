package com.intuit.chirp.tweets.controller;

import com.intuit.chirp.tweets.model.domain.User;
import com.intuit.chirp.tweets.model.dto.UserFollowing;
import com.intuit.chirp.tweets.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.intuit.chirp.tweets.utils.SecurityUtils.getUserName;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping
    public List<UserFollowing> users(@AuthenticationPrincipal Jwt principal) {
        String userName = getUserName(principal);
        User user = userService.getUserByLdapId(userName);
        return userService.getUsersWithFollowing(user.getId());
    }

}
