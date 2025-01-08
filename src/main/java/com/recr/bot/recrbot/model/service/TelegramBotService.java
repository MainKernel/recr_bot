package com.recr.bot.recrbot.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.ExceptionHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramException;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.recr.bot.recrbot.model.handlers.UserFlowHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// Telegram bot service that running asynchronicly from main thread

@Service
@Slf4j
@RequiredArgsConstructor
public class TelegramBotService{
    // Loadin telegram bot token from properties file
    @Value("${telegram.bot.token}") 
    private String telegramBotToken;

    private final UserFlowHandler userFlowHandler;


    // Main async bot method
    @Async
    public void startBot(){
        // Creating new telegram bot instance
        TelegramBot telegramBot = new TelegramBot(telegramBotToken);

    telegramBot.setUpdatesListener(new UpdatesListener() {
        @Override
        public int process(List<Update> updates) {
    
            // process updates updates
            if(!updates.isEmpty()){
                userFlowHandler.handle(telegramBot, updates);
            }
            
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }
   
    },
     // Create Exception Handler
    (ExceptionHandler) new ExceptionHandler() {
        @Override
        public void onException(TelegramException e)
        {
            if (e.response() != null) {
                // got bad response from telegram
                log.error(e.toString() + " got bad response from telegram");
            } else {
                // probably network error
                log.error(e.toString() + " probably network error");
            }
        }
    });
}
}
