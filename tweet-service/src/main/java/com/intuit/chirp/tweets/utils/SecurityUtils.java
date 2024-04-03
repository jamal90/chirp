package com.intuit.chirp.tweets.utils;

import org.springframework.security.oauth2.jwt.Jwt;

public class SecurityUtils {
    public static String getUserName(Jwt principal) {
        return principal.getClaims().get("preferred_username").toString();
    }
}
