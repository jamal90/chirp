package com.intuit.chirp.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {

//        csrf
//            .csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
//            .csrfTokenRequestHandler(new ServerCsrfTokenRequestAttributeHandler())

        return httpSecurity
                .authorizeExchange(pathMatchers -> pathMatchers.anyExchange().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .cors(cors -> cors.configurationSource(getUrlBasedCorsConfigurationSource()))
                .build();
    }

    private UrlBasedCorsConfigurationSource getUrlBasedCorsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3001");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource allowLocalCors = new UrlBasedCorsConfigurationSource();
        allowLocalCors.registerCorsConfiguration("/**", config);
        return allowLocalCors;
    }
}
