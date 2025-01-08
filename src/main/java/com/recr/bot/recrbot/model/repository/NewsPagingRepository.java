package com.recr.bot.recrbot.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.recr.bot.recrbot.model.entity.NewsEntity;

@Repository
public interface NewsPagingRepository extends PagingAndSortingRepository<NewsEntity, Long>{
    
}
