package com.recr.bot.recrbot.model.handlers.job;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.request.SendMessage;
import com.recr.bot.recrbot.controller.KeyboardController;
import com.recr.bot.recrbot.model.entity.CandidatEntity;
import com.recr.bot.recrbot.model.handlers.HandlerChain;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AskWorkExpirienceHandler implements JobApplicationFlowHandler {
    private JobApplicationFlowHandler nextHandler;
    @Override
    public SendMessage handle(Long chatId, String message, ConcurrentHashMap<Long, String> userState,
            ConcurrentHashMap<Long, CandidatEntity> userData, String state, KeyboardController keyboardController) {
                if(state.equals("ASK_WORK_EXPIRIENCE")){
                    userData.get(chatId).setEducationalInstitution(message);
                    userState.put(chatId, "ASK_PHONE");
                    return new SendMessage(chatId, "Вкажіть досвід роботи. (Необхідно вказати дати останніх місць роботи та ким працював)")
                    .replyMarkup(KeyboardController.mainMenu());
                } else if(nextHandler != null) {
                    return nextHandler.handle(chatId, message, userState, userData, state, keyboardController);

                } else {
                    log.info("Error occured due to AskWorkExpirienceHandler processing.");
                    return HandlerChain.unhandledConversation(chatId, message, userState, userData);
                }

    }

    @Override
    public void setNextHandler(JobApplicationFlowHandler jobApplicationFlowHandler) {
        this.nextHandler = jobApplicationFlowHandler;
    }

}
