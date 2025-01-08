package com.recr.bot.recrbot.model.handlers.job;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.request.SendMessage;
import com.recr.bot.recrbot.controller.KeyboardController;
import com.recr.bot.recrbot.model.entity.CandidatEntity;
import com.recr.bot.recrbot.model.entity.enums.MilitaryStatus;
import com.recr.bot.recrbot.model.handlers.HandlerChain;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AskLeftDutyHandler implements JobApplicationFlowHandler {
    private JobApplicationFlowHandler nextHandler;

    @Override
    public SendMessage handle(Long chatId, String message, ConcurrentHashMap<Long, String> userState,
            ConcurrentHashMap<Long, CandidatEntity> userData, String state, KeyboardController keyboardController) {
        if (state.equals("ASK_LEFT_DUTY")) {
            switch (message) {
                case "yes":
                    userData.get(chatId).setStatus(MilitaryStatus.leftDuty);
                    userState.put(chatId, "ASK_BRUNCH");
                    return new SendMessage(chatId, "В якому роді військ проходили службу?")
                            .replyMarkup(keyboardController.brunchOfServiceKeyboard());
                case "no":
                    userData.get(chatId).setStatus(MilitaryStatus.onDuty);
                    userState.put(chatId, "ASK_BRUNCH");
                    return new SendMessage(chatId, "В якому роді військ проходте службу?")
                    .replyMarkup(keyboardController.brunchOfServiceKeyboard());
                default:
                    log.info("Error occured due to AskLeftDutyHandler processing.");
                    return HandlerChain.unhandledConversation(chatId, message, userState, userData);
            }
        } else if (nextHandler != null) {
            return nextHandler.handle(chatId, message, userState, userData, state, keyboardController);
        } else {
            log.info("Error occured due to AskLeftDutyHandler processing.");
            return HandlerChain.unhandledConversation(chatId, message, userState, userData);
        }
    }

    @Override
    public void setNextHandler(JobApplicationFlowHandler jobApplicationFlowHandler) {
        this.nextHandler = jobApplicationFlowHandler;
    }

}
