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

        public InlineKeyboardMarkup yesNoKeyboard() {
                return new InlineKeyboardMarkup(
                                new InlineKeyboardButton[][] {
                                                new InlineKeyboardButton[] {
                                                                new InlineKeyboardButton("Так").callbackData("yes")
                                                },
                                                new InlineKeyboardButton[] {
                                                                new InlineKeyboardButton("Ні").callbackData("no")
                                                }
                                });
        }

        public InlineKeyboardMarkup brunchOfServiceKeyboard() {
                return new InlineKeyboardMarkup(
                                new InlineKeyboardButton[][] {
                                                new InlineKeyboardButton[] {
                                                                new InlineKeyboardButton("ТРО").callbackData("tro"),
                                                                new InlineKeyboardButton("ССО").callbackData("sso"),
                                                                new InlineKeyboardButton("ГУР").callbackData("gur")

                                                },
                                                new InlineKeyboardButton[] {
                                                                new InlineKeyboardButton("ДШВ").callbackData("dsv"),
                                                                new InlineKeyboardButton("МП").callbackData("mp"),
                                                                new InlineKeyboardButton("СВ").callbackData("sv")

                                                },
                                                new InlineKeyboardButton[] {
                                                                new InlineKeyboardButton("ВМС").callbackData("vms"),
                                                                new InlineKeyboardButton("НГУ").callbackData("ngu"),
                                                                new InlineKeyboardButton("ДПСУ").callbackData("dpsu")

                                                },
                                                new InlineKeyboardButton[] {
                                                                new InlineKeyboardButton("ПС").callbackData("ps"),
                                                                new InlineKeyboardButton("ГШ").callbackData("gh"),
                                                                new InlineKeyboardButton("МО").callbackData("mo")

                                                },
                                                new InlineKeyboardButton[] {
                                                                new InlineKeyboardButton("СилиБС").callbackData("sbs"),
                                                                new InlineKeyboardButton("СБУ").callbackData("sbu")
                                                }
                                });
        }

        public InlineKeyboardMarkup educationSelectionKeyboard() {
                return new InlineKeyboardMarkup(
                                new InlineKeyboardButton[][] {
                                                new InlineKeyboardButton[] {
                                                                new InlineKeyboardButton("Вища")
                                                                                .callbackData("higherEducation")
                                                },
                                                new InlineKeyboardButton[] {
                                                                new InlineKeyboardButton("Середня спеціальна")
                                                                                .callbackData("specialEducation")
                                                },
                                                new InlineKeyboardButton[] {
                                                                new InlineKeyboardButton("Середня")
                                                                                .callbackData("mediumEducation")
                                                }
                                });
        }

        public Keyboard getPhone(){
                return new ReplyKeyboardMarkup( new KeyboardButton[]{
                        new KeyboardButton("Надати номер телефону").requestContact(true),
                }
                );
                
        }
}