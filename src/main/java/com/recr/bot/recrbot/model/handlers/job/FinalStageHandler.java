package com.recr.bot.recrbot.model.handlers.job;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.request.SendMessage;
import com.recr.bot.recrbot.controller.KeyboardController;
import com.recr.bot.recrbot.model.entity.CandidatEntity;
import com.recr.bot.recrbot.model.handlers.HandlerChain;
import com.recr.bot.recrbot.model.service.CandidatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class FinalStageHandler implements JobApplicationFlowHandler {
    private final CandidatService candidatService;
    private JobApplicationFlowHandler nextHandler;
    @Override
    public SendMessage handle(Long chatId, String message, ConcurrentHashMap<Long, String> userState,
            ConcurrentHashMap<Long, CandidatEntity> userData, String state, KeyboardController keyboardController) {
        if(state.equals("FINAL_STAGE")){
            userData.get(chatId).setPhoneNumber(message);
            candidatService.saveCandidateJobApplication(userData.get(chatId));
                userState.remove(chatId);
                userData.remove(chatId);
                return new SendMessage(chatId,
                        "Ваша заявка буде оброблена найближчим часом. Дякуємо за те що звернулись в Центр Рекрутингу Сил ТрО ЗСУ)")
                .replyMarkup(KeyboardController.mainMenu());

        } else if (nextHandler != null){
            return nextHandler.handle(chatId, message, userState, userData, state, keyboardController);

        } else {
            log.info("Error occured due to the FinalStageProcessing");
            return HandlerChain.unhandledConversation(chatId, message, userState, userData);
        }
    }

    @Override
    public void setNextHandler(JobApplicationFlowHandler jobApplicationFlowHandler) {
        this.nextHandler = jobApplicationFlowHandler;
    }

}
