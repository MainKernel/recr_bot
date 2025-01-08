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
public class AskPhoneHandler implements JobApplicationFlowHandler {
    private JobApplicationFlowHandler nextHanler;

    @Override
    public SendMessage handle(Long chatId, String message, ConcurrentHashMap<Long, String> userState,
            ConcurrentHashMap<Long, CandidatEntity> userData, String state, KeyboardController keyboardController) {
        if (state.equals("ASK_PHONE")) {
            userData.get(chatId).setWorkExp(message);
            userState.put(chatId, "FINAL_STAGE");
            return new SendMessage(chatId,
                    "Вкажіть будь-ласка свій контактний номер телефону, для зручного зв'язку з вами")
                    .replyMarkup(KeyboardController.mainMenu())
                    .replyMarkup(keyboardController.getPhone());
        } else if (nextHanler != null) {
            return nextHanler.handle(chatId, message, userState, userData, state, keyboardController);

        } else {
            log.info("Error occured due to AskPhoneHandler processing");
            return HandlerChain.unhandledConversation(chatId, message, userState, userData);
        }
    }

    @Override
    public void setNextHandler(JobApplicationFlowHandler jobApplicationFlowHandler) {
        this.nextHanler = jobApplicationFlowHandler;
    }

}
