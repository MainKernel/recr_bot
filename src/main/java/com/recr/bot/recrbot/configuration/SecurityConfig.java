package com.recr.bot.recrbot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(req -> req
                                                .requestMatchers(HttpMethod.GET, "/login").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/", "/candidats/edit/{id}", "/add-news")
                                                .hasAnyAuthority("ADMIN", "USER")
                                                .requestMatchers(HttpMethod.GET, "/css/**", "/images/**", "/js/**",
                                                                "/Icons/**", "/favicon.ico")
                                                .hasAnyAuthority("ADMIN", "USER")
                                                .requestMatchers(HttpMethod.GET, "/", "/candidats/new",
                                                                "/candidats/old", "/candidats/take/{id}",
                                                                "/candidats/edit/{id}", "/candidats/{id}", "/news/{id}")
                                                .hasAnyAuthority("ADMIN", "USER"))
                                .formLogin(l -> l
                                                .defaultSuccessUrl("/")
                                                .loginPage("/login"))
                                .build();
        }

}