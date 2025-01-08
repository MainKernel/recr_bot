package com.recr.bot.recrbot.model.service;

import org.springframework.stereotype.Service;

import com.recr.bot.recrbot.model.repository.UserRepository;
import com.recr.bot.recrbot.model.entity.UserEntity;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * 
     * @param username String that is trying to find in database
     * @return UserEntity
     * @exception IlligalArgumentException throwed if there is no user with this username in database
     */
    // TO DO write InvalidParameterException insted of IllegalArgumentEcxeption
    public UserEntity getUserByUsername(String username) throws IllegalArgumentException {
        if(!username.isBlank()){
            return userRepository.findByUsername(username);
        } else{
            throw new IllegalArgumentException();
        }
    }

    public void save(UserEntity userEntity){
        userRepository.save(userEntity);
    }

}
