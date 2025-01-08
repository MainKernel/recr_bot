package com.recr.bot.recrbot.model.handlers;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.recr.bot.recrbot.controller.KeyboardController;
import com.recr.bot.recrbot.model.entity.CandidatEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFlowHandler {
    private final KeyboardController keyboardController;
    private final HandlerChain jobApplicationStateHandler;
    // State for user
    private final ConcurrentHashMap<Long, String> userState = new ConcurrentHashMap<>();
    // User steps
    private final ConcurrentHashMap<Long, CandidatEntity> userData = new ConcurrentHashMap<>();

    public void handle(TelegramBot telegramBot, List<Update> updates) {

        // Check updates for any data
        if (!updates.isEmpty()) {
            // Extract every update from update list
            updates.forEach(e -> {
                SendMessage message;
                // Get text message from update
                Optional<String> chatText = Optional.ofNullable(e.message() != null ? e.message().text() : null);
                // Get callback data from update
                Optional<String> callbackData = Optional
                        .ofNullable(e.callbackQuery() != null ? e.callbackQuery().data() : null);
                Optional<String> phoneNumber = Optional
                        .ofNullable(e.message() != null && e.message().contact() != null
                                ? e.message().contact().phoneNumber()
                                : null);

                // Check that any text is present
                if (chatText.isPresent()) {
                    Long chatId = e.message().from().id();
                    String userInput = chatText.get();

                    switch (chatText.get()) {
                        case "/start":
                            message = startMessage(chatId);
                            telegramBot.execute(message);
                            break;
                        default:
                            if (userState.get(chatId) != null) {
                                telegramBot.execute(jobApplicationStateHandler.jobApplicationState(chatId, userInput,
                                        userState, userData));
                            }
                            break;
                    }

                }

                // Check that any callback data is present
                else if (callbackData.isPresent()) {
                    Long chatId = e.callbackQuery().from().id();
                    String calbackText = e.callbackQuery().data().toString();

                    switch (callbackData.get()) {
                        case "jobApplication":
                            // Fisrs candidat init
                            // TODO may occured problem
                            CandidatEntity candidatEntity = CandidatEntity.builder().build();
                            candidatEntity.setChatId(chatId);
                            userData.put(chatId, candidatEntity);
                            userState.put(chatId, "START");
                            message = jobApplicationStateHandler.jobApplicationState(chatId, "", userState, userData);
                            telegramBot.execute(message);
                            break;
                        case "startInfo":
                            telegramBot.execute(
                                    new SendMessage(chatId, "Нові інформування відсутні")
                                            .replyMarkup(keyboardController.startKeyboard()));
                            break;
                        case "home":
                            userState.remove(chatId);
                            userData.remove(chatId);
                            telegramBot.execute(startMessage(chatId));
                            break;
                        default:
                            telegramBot.execute(jobApplicationStateHandler.jobApplicationState(chatId, calbackText,
                                    userState, userData));
                            break;
                    }
                } else if (phoneNumber.isPresent()) {
                    Long userId = e.message().contact().userId();
                    String phone = phoneNumber.get().toString();
                    telegramBot.execute(jobApplicationStateHandler.jobApplicationState(userId, phone,
                            userState, userData));

                }

            });
        }
    }

    private SendMessage startMessage(Long chatId) {
        return new SendMessage(chatId, "Оберіть один з пунктів меню")
                .replyMarkup(keyboardController.startKeyboard());
    }

}