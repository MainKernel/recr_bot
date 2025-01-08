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
public class AskDutyHandler implements JobApplicationFlowHandler{
    private JobApplicationFlowHandler nextHandler;
    
    @Override
    public SendMessage handle(Long chatId, String message, ConcurrentHashMap<Long, String> userState,
            ConcurrentHashMap<Long, CandidatEntity> userData, String state, KeyboardController keyboardController) {
                if (state.equals("ASK_DUTY")) {
                    switch (message) {
                        case "yes":
                            userData.get(chatId).setStatus(MilitaryStatus.onDuty);
                            userState.put(chatId, "ASK_LEFT_DUTY");
                            return new SendMessage(chatId, "Чи знаходитись ви в СЗЧ.")
                            .replyMarkup(KeyboardController.mainMenu())
                            .replyMarkup(keyboardController.yesNoKeyboard());
                        case "no":
                            userData.get(chatId).setStatus(MilitaryStatus.civilian);
                            userState.put(chatId, "ASK_HELTH");
                            return new SendMessage(chatId, "Як оцінюєте свій стан здоров'я. Якщо маєте рішення ВЛК, також вкажіть який стан здоров'я за висновком влк.")
                            .replyMarkup(KeyboardController.mainMenu());
                        default:
                        log.debug("Error occured due to the AskDutyHandler processing");
                        return HandlerChain.unhandledConversation(chatId, message, userState, userData);
                    }
                    
                } else if(nextHandler != null){
                    return nextHandler.handle(chatId, message, userState, userData, state, keyboardController);
                } else{
                    log.debug("Error occured due to the AskDutyHandler processing");
                    return HandlerChain.unhandledConversation(chatId, message, userState, userData);
                }
    }

    @Override
    public void setNextHandler(JobApplicationFlowHandler jobApplicationFlowHandler) {
        this.nextHandler = jobApplicationFlowHandler;
    }
    
}
