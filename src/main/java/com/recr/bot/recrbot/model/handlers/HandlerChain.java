package com.recr.bot.recrbot.model.handlers;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.request.SendMessage;
import com.recr.bot.recrbot.controller.KeyboardController;
import com.recr.bot.recrbot.model.entity.CandidatEntity;
import com.recr.bot.recrbot.model.handlers.job.AskAgeHandler;
import com.recr.bot.recrbot.model.handlers.job.AskBrunchHandler;
import com.recr.bot.recrbot.model.handlers.job.AskCombatParticipantHandler;
import com.recr.bot.recrbot.model.handlers.job.AskDutyHandler;
import com.recr.bot.recrbot.model.handlers.job.AskEducationHandler;
import com.recr.bot.recrbot.model.handlers.job.AskHelthHandler;
import com.recr.bot.recrbot.model.handlers.job.AskInstitutionHandler;
import com.recr.bot.recrbot.model.handlers.job.AskLeftDutyHandler;
import com.recr.bot.recrbot.model.handlers.job.AskNameHandler;

import com.recr.bot.recrbot.model.handlers.job.AskPhoneHandler;
import com.recr.bot.recrbot.model.handlers.job.AskProfessionHandler;
import com.recr.bot.recrbot.model.handlers.job.AskRankHandler;
import com.recr.bot.recrbot.model.handlers.job.AskWorkExpirienceHandler;
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
        JobApplicationFlowHandler askDuty = new AskDutyHandler();
        JobApplicationFlowHandler askLeftDuty = new AskLeftDutyHandler();
        JobApplicationFlowHandler askBrunchHandler = new AskBrunchHandler();
        JobApplicationFlowHandler askRank = new AskRankHandler();
        JobApplicationFlowHandler askCombatParticipant = new AskCombatParticipantHandler();
        JobApplicationFlowHandler askHelthHandler = new AskHelthHandler();
        JobApplicationFlowHandler askEducation = new AskEducationHandler();
        JobApplicationFlowHandler askProfession = new AskProfessionHandler();
        JobApplicationFlowHandler askInstitutionHandler = new AskInstitutionHandler();
        JobApplicationFlowHandler askWorkExp = new AskWorkExpirienceHandler();
        JobApplicationFlowHandler askPhone = new AskPhoneHandler();
        JobApplicationFlowHandler finalStage = finalStageHandler;
        

        
        start.setNextHandler(askName);
        askName.setNextHandler(askAge);
        askAge.setNextHandler(askDuty);
        askDuty.setNextHandler(askLeftDuty);
        askLeftDuty.setNextHandler(askBrunchHandler);
        askBrunchHandler.setNextHandler(askRank);
        askRank.setNextHandler(askCombatParticipant);
        askCombatParticipant.setNextHandler(askHelthHandler);
        askHelthHandler.setNextHandler(askEducation);
        askEducation.setNextHandler(askProfession);
        askProfession.setNextHandler(askInstitutionHandler);
        askInstitutionHandler.setNextHandler(askWorkExp);
        askWorkExp.setNextHandler(askPhone);
        askPhone.setNextHandler(finalStage);

        
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
