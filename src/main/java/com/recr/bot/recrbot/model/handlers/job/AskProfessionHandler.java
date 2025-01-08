package com.recr.bot.recrbot.model.handlers.job;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.request.SendMessage;
import com.recr.bot.recrbot.controller.KeyboardController;
import com.recr.bot.recrbot.model.entity.CandidatEntity;
import com.recr.bot.recrbot.model.entity.enums.Education;
import com.recr.bot.recrbot.model.handlers.HandlerChain;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AskProfessionHandler implements JobApplicationFlowHandler {
    private JobApplicationFlowHandler nextHandler;

    @Override
    public SendMessage handle(Long chatId, String message, ConcurrentHashMap<Long, String> userState,
            ConcurrentHashMap<Long, CandidatEntity> userData, String state, KeyboardController keyboardController) {

        if (state.equals("ASK_PROFESSION")) {
            boolean isValid = false;
            switch (message) {
                case "higherEducation":
                    userData.get(chatId).setEducation(Education.HIGHER);
                    isValid = true;
                    break;
                case "specialEducation":
                    userData.get(chatId).setEducation(Education.SPECIAL);
                    isValid = true;
                    break;
                case "mediumEducation":
                    userData.get(chatId).setEducation(Education.MEDIUM);
                    isValid = true;
                    break;
            }
            if(isValid){
                userState.put(chatId, "ASK_INSTITUTION");
            return new SendMessage(chatId, "Вкажіть вашу спеціальність.")
                    .replyMarkup(KeyboardController.mainMenu());
            } else{
                userState.put(chatId, "ASK_PROFESSION");
            return new SendMessage(chatId, "Вкажіть рівень вашої освіти.")
                    .replyMarkup(keyboardController.educationSelectionKeyboard());

            }
            
        } else if (nextHandler != null) {
            return nextHandler.handle(chatId, message, userState, userData, state, keyboardController);

        } else {
            log.info("Error occured due to AskProfessionHandler processing");
            return HandlerChain.unhandledConversation(chatId, message, userState, userData);
        }
    }

    @Override
    public void setNextHandler(JobApplicationFlowHandler jobApplicationFlowHandler) {
        this.nextHandler = jobApplicationFlowHandler;
    }

}
