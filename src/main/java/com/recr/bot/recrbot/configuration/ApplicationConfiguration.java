package com.recr.bot.recrbot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan(value = "com/recr/bot/recrbot")
public class ApplicationConfiguration {

@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder(12);
}
}
