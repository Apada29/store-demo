package com.ing.demos.config.user;

import com.ing.demos.web.exception.UserBadCredentialsException;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BasicUserDetailsService implements UserDetailsService {

    private final Map<String, UserDetails> roles = new HashMap<>();

    @PostConstruct
    public void init() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        roles.put("admin", User.withUsername("admin")
                .password(encoder.encode("admin"))
                .roles("USER", "ADMIN").build());
        roles.put("user", User.withUsername("user")
                .password(encoder.encode("user"))
                .roles("USER").build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(roles.get(username)).orElseThrow(UserBadCredentialsException::new);
    }

}