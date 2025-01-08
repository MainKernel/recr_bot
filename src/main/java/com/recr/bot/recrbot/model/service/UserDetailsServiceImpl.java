package com.recr.bot.recrbot.model.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 *  Custom UserDetailsService for loading users from database
 */

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username){
        try{
            return userService.getUserByUsername(username);
        } catch (IllegalArgumentException ex){
            throw new UsernameNotFoundException("User not found");
        }
        
    }

}
