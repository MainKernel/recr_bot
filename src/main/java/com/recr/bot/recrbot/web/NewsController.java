package com.recr.bot.recrbot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.recr.bot.recrbot.model.entity.NewsEntity;
import com.recr.bot.recrbot.model.service.NewsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @PostMapping("/add-news")
    public ModelAndView postMethodName(@RequestParam("message-title") String title,
            @RequestParam("message-text") String text) {
        NewsEntity newsEntity = NewsEntity.builder().title(title).text(text).build();
        newsService.saveNews(newsEntity);


        return new ModelAndView("redirect:/");
    }
}
