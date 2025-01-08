package com.recr.bot.recrbot.model.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.recr.bot.recrbot.model.entity.UserEntity;
import com.recr.bot.recrbot.model.entity.enums.Role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminCreationService {
    @Value("${WEB_USERNAME}")
    private String username;
    @Value("${WEB_PASSWORD}")
    private String password;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Async
    public void run() {
        UserEntity userByUsername = userService.getUserByUsername(username);
        if (userByUsername == null) {
            userService.save(UserEntity.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .role(Role.ADMIN)
                    .firstName("admin")
                    .secondName("admin")
                    .build());
        }

    }

}
