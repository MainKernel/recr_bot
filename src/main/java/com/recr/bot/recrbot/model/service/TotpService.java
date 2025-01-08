package com.recr.bot.recrbot.model.service;

import org.springframework.stereotype.Service;

import com.recr.bot.recrbot.model.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TotpService {
    private final GAService gaService;
    private final UserService userService;


    public String imageForUser(String username){
        String key = gaService.generateKey();

        UserEntity userByUsername = userService.getUserByUsername(username);
        userByUsername.setTotpCode(key);
        userByUsername.setMfa(true);
        userService.save(userByUsername);

        return gaService.generateQRUrl(key, username);
    }
    
}
