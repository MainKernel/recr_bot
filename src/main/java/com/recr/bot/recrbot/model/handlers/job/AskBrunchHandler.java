package com.recr.bot.recrbot.model.handlers.job;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.request.SendMessage;
import com.recr.bot.recrbot.controller.KeyboardController;
import com.recr.bot.recrbot.model.entity.CandidatEntity;
import com.recr.bot.recrbot.model.handlers.HandlerChain;
import com.recr.bot.recrbot.utils.JobApplicationUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AskBrunchHandler implements JobApplicationFlowHandler {
    private JobApplicationFlowHandler nextHandler;

    @Override
    public SendMessage handle(Long chatId, String message, ConcurrentHashMap<Long, String> userState,
            ConcurrentHashMap<Long, CandidatEntity> userData, String state, KeyboardController keyboardController) {
        if (state.equals("ASK_BRUNCH")) {
            String branchMapper = new JobApplicationUtils().branchMapper(message);
            userData.get(chatId).setBranchOfService(branchMapper);
            userState.put(chatId, "ASK_RANK");
            return new SendMessage(chatId, "Яке ваше військове звання?")
                    .replyMarkup(KeyboardController.mainMenu());
        } else if (nextHandler != null) {
            return nextHandler.handle(chatId, message, userState, userData, state, keyboardController);
        } else {
            log.info("Error occured due to AskBrunchHandler processing.");
            return HandlerChain.unhandledConversation(chatId, message, userState, userData);
        }
    }

    @Override
    public void setNextHandler(JobApplicationFlowHandler jobApplicationFlowHandler) {
        this.nextHandler = jobApplicationFlowHandler;
    }

}
