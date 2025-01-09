package com.recr.bot.recrbot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

@Component
public class KeyboardController {
        @Value("${rc.site.url}")
        private String recrutingCenterSiteUrl;

        public InlineKeyboardMarkup startKeyboard() {
                return new InlineKeyboardMarkup(
                                new InlineKeyboardButton[][] {
                                                new InlineKeyboardButton[] {
                                                                new InlineKeyboardButton("Подати заявку на вакансію")
                                                                                .callbackData("jobApplication")
                                                },
                                                new InlineKeyboardButton[] {
                                                                new InlineKeyboardButton("Сайт Рекрутингового Центру")
                                                                                .url(recrutingCenterSiteUrl)
                                                },
                                                new InlineKeyboardButton[] {
                                                                new InlineKeyboardButton("Інформування")
                                                                                .callbackData("startInfo")
                                                }
                                });
        }

        public static InlineKeyboardMarkup mainMenu() {
                return new InlineKeyboardMarkup(
                                new InlineKeyboardButton[] {
                                                new InlineKeyboardButton("Повернутися на головну сторінку")
                                                                .callbackData("home")
                                });
        }

        public Keyboard getPhone() {
                return new ReplyKeyboardMarkup(new KeyboardButton[] {
                                new KeyboardButton("Надати номер телефону").requestContact(true),
                });

        }
}