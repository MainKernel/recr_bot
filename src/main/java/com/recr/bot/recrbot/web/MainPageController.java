package com.recr.bot.recrbot.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.recr.bot.recrbot.model.entity.NewsEntity;
import com.recr.bot.recrbot.model.service.NewsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainPageController {
    private final NewsService newsService;

    @GetMapping("/")
    public ModelAndView getMethodName() {
        ModelAndView mv = new ModelAndView("index");

        // Створюємо запит пагінації з сортуванням за датою
        Page<NewsEntity> pageSortedByTime = newsService.pageSortedByTime(0);

        // Отримуємо розмір та контент сторінки
        int size = pageSortedByTime.getSize();
        List<NewsEntity> content = pageSortedByTime.getContent();

        // Додаємо до моделі новини та кількість елементів
        mv.addObject("news", content);
        mv.addObject("newsLength", size);
        mv.addObject("currentPage", 1);
        mv.addObject("totalPages", pageSortedByTime.getTotalPages());

        return mv;
    }

    @GetMapping("/news/{id}")
    public ModelAndView getPage(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("index");

        // Отримуємо новини на основі пагінації
        Page<NewsEntity> pageSortedByTime = newsService.pageSortedByTime(id);

        // Отримуємо контент сторінки та її розмір
        int size = pageSortedByTime.getSize();
        List<NewsEntity> content = pageSortedByTime.getContent();

        // Додаємо новини та інформацію про сторінки до моделі
        mv.addObject("news", content);
        mv.addObject("newsLength", size);
        mv.addObject("currentPage", id);
        mv.addObject("totalPages", pageSortedByTime.getTotalPages());

        return mv;
    }

}
