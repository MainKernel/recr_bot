package com.recr.bot.recrbot.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.recr.bot.recrbot.model.entity.CandidatEntity;

@Repository
public interface CandidatRepository extends JpaRepository<CandidatEntity, Long>{

    @Query("SELECT c FROM CandidatEntity c WHERE c.recruter IS NULL OR c.recruter = ''")
    List<CandidatEntity> findCandidatesWithoutRecruiter();

    @Query("SELECT c FROM CandidatEntity c WHERE c.recruter IS NOT NULL AND c.recruter != ''")
    List<CandidatEntity> findCandidatWithRecruiter();

    List<CandidatEntity> findCandidatsByChatId(Long chatId);
    
}
