package com.recr.bot.recrbot.model.handlers.job;

import java.util.concurrent.ConcurrentHashMap;

import com.pengrad.telegrambot.request.SendMessage;
import com.recr.bot.recrbot.controller.KeyboardController;
import com.recr.bot.recrbot.model.entity.CandidatEntity;

public interface JobApplicationFlowHandler {
    
    SendMessage handle(Long chatId, String message,
            ConcurrentHashMap<Long, String> userState, ConcurrentHashMap<Long, CandidatEntity> userData,
            String state, KeyboardController keyboardController);

    void setNextHandler(JobApplicationFlowHandler jobApplicationFlowHandler);

}
