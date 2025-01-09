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
public class AskHelthHandler implements JobApplicationFlowHandler {
    private JobApplicationFlowHandler nextHandler;

    @Override
    public SendMessage handle(Long chatId, String message, ConcurrentHashMap<Long, String> userState,
            ConcurrentHashMap<Long, CandidatEntity> userData, String state, KeyboardController keyboardController) {
        if (state.equals("ASK_HELTH")) {
            userData.get(chatId).setHelthStatus(message);
            userState.put(chatId, "ASK_PROFESSION");
            return new SendMessage(chatId, "Зрозуміло, дякую за інформацію! Яка у вас спеціальність або професійний напрямок?")
                    .replyMarkup(KeyboardController.mainMenu());
        } else if (nextHandler != null) {
            return nextHandler.handle(chatId, message, userState, userData, state, keyboardController);
        } else {
            log.info("Error occured due to AskHelthHandler processing.");
            return HandlerChain.unhandledConversation(chatId, message, userState, userData);
        }
    }

    @Override
    public void setNextHandler(JobApplicationFlowHandler jobApplicationFlowHandler) {
        this.nextHandler = jobApplicationFlowHandler;
    }

}
