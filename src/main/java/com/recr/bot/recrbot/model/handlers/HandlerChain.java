package com.recr.bot.recrbot.model.handlers;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.request.SendMessage;
import com.recr.bot.recrbot.controller.KeyboardController;
import com.recr.bot.recrbot.model.entity.CandidatEntity;
import com.recr.bot.recrbot.model.handlers.job.AskAgeHandler;
import com.recr.bot.recrbot.model.handlers.job.AskHelthHandler;
import com.recr.bot.recrbot.model.handlers.job.AskNameHandler;

import com.recr.bot.recrbot.model.handlers.job.AskProfessionHandler;
import com.recr.bot.recrbot.model.handlers.job.FinalStageHandler;
import com.recr.bot.recrbot.model.handlers.job.JobApplicationFlowHandler;
import com.recr.bot.recrbot.model.handlers.job.StartHandler;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HandlerChain {
    private final KeyboardController keyboardController;
    private JobApplicationFlowHandler firstHandler;

    public HandlerChain(KeyboardController keyboardController, FinalStageHandler finalStageHandler){
        this.keyboardController = keyboardController;

        JobApplicationFlowHandler start = new StartHandler();
        JobApplicationFlowHandler askName = new AskNameHandler();
        JobApplicationFlowHandler askAge = new AskAgeHandler();
        JobApplicationFlowHandler askHelth = new AskHelthHandler();
        JobApplicationFlowHandler askProfession = new AskProfessionHandler();
        JobApplicationFlowHandler finalStage = finalStageHandler;
        

        
        start.setNextHandler(askName);
        askName.setNextHandler(askAge);
        askAge.setNextHandler(askHelth);
        askHelth.setNextHandler(askProfession);
        askProfession.setNextHandler(finalStage);

        
        this.firstHandler = start;

    }

    public SendMessage jobApplicationState(Long chatId, String message,
            ConcurrentHashMap<Long, String> userState, ConcurrentHashMap<Long, CandidatEntity> userData) {

        String state = userState.getOrDefault(chatId, "START");
        
        return firstHandler.handle(chatId, message, userState, userData, state, keyboardController);
    }

    public static SendMessage unhandledConversation(Long chatId, String message,
    ConcurrentHashMap<Long, String> userState, ConcurrentHashMap<Long, CandidatEntity> userData){
        userState.remove(chatId);
        userData.remove(chatId);
        return new SendMessage(chatId, "Нажаль щось пішло не так, але наши фіксики вже проінформовані про це :з")
        .replyMarkup(KeyboardController.mainMenu());
    }

}
