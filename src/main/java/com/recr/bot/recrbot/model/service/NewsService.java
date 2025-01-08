package com.recr.bot.recrbot.model.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.recr.bot.recrbot.model.entity.NewsEntity;
import com.recr.bot.recrbot.model.repository.NewsPagingRepository;
import com.recr.bot.recrbot.model.repository.NewsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsPagingRepository newsPagingRepository;



    public void saveNews(NewsEntity newsEntity){
        newsRepository.save(newsEntity);
    }

    public Page<NewsEntity> pageSortedByTime(int id){
        Pageable sortedByTime = PageRequest.of(0, 9, Sort.by("newsPublishTime").descending());
        return newsPagingRepository.findAll(sortedByTime);
    }
}
