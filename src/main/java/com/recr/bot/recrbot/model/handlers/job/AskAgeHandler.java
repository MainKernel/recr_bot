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
public class AskAgeHandler implements JobApplicationFlowHandler {
    private JobApplicationFlowHandler nextHandler;

    @Override
    public SendMessage handle(Long chatId, String message, ConcurrentHashMap<Long, String> userState,
            ConcurrentHashMap<Long, CandidatEntity> userData, String state, KeyboardController keyboardController) {
        if (state.equals("ASK_AGE")) {
            try {
                userData.get(chatId).setAge(Integer.parseInt(message));
                userState.put(chatId, "ASK_DUTY");
                return new SendMessage(chatId,
                "Чи перебуваєте ви на військовій службі? Якщо в СЗЧ то обирайте \"Так\"")
                .replyMarkup(KeyboardController.mainMenu())
                .replyMarkup(keyboardController.yesNoKeyboard());

            } catch (NumberFormatException ex) {
                log.error(ex.getMessage());
                userState.put(chatId, "ASK_AGE");
                return new SendMessage(chatId, "Введіть роки цифрами.")
                .replyMarkup(KeyboardController.mainMenu());

            }
        } else if(nextHandler != null){
           return nextHandler.handle(chatId, message, userState, userData, state, keyboardController);
        } else{
            log.debug("error occured due to AskAgeHadnler processing");
            return HandlerChain.unhandledConversation(chatId, message, userState, userData);
        }
    }

    @Override
    public void setNextHandler(JobApplicationFlowHandler jobApplicationFlowHandler) {
        this.nextHandler = jobApplicationFlowHandler;
    }

}
