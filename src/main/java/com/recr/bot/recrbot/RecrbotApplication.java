package com.recr.bot.recrbot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.recr.bot.recrbot.model.service.AdminCreationService;
import com.recr.bot.recrbot.model.service.TelegramBotService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAsync
@Slf4j
public class RecrbotApplication {
	public static void main(String[] args) {
		SpringApplication.run(RecrbotApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TelegramBotService telegramBotService, AdminCreationService creationService){
		return args -> {
			telegramBotService.startBot();
        	log.info("Telegram bot service started in background");
			creationService.run();
			log.info("Admin user created");
		};
	}
}
