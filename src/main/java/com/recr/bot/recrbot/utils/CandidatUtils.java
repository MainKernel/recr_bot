package com.recr.bot.recrbot.utils;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.recr.bot.recrbot.model.dto.CandidatEntityEditFormDto;
import com.recr.bot.recrbot.model.dto.CandidatFullDto;
import com.recr.bot.recrbot.model.dto.CandidatShortDto;
import com.recr.bot.recrbot.model.entity.CandidatEntity;

@Component
public class CandidatUtils {

    public CandidatShortDto EntityToShortDtoMapper(CandidatEntity candidatEntity) {
        return CandidatShortDto.builder()
        .id(candidatEntity.getId())
        .name(candidatEntity.getName())
        .age(candidatEntity.getAge())
        .atempt(candidatEntity.getAtempt())
        .profession(candidatEntity.getProfession())
        .recruter(candidatEntity.getRecruter())
        .build();
    }

    public CandidatFullDto EntityToFullDtoMapper(CandidatEntity candidatEntity){
        return CandidatFullDto.builder()
        .id(candidatEntity.getId())
        .name(candidatEntity.getName())
        .phoneNumber(candidatEntity.getPhoneNumber())
        .age(candidatEntity.getAge())
        .profession(candidatEntity.getProfession())
        .helthStatus(candidatEntity.getHelthStatus())
        .recruter(candidatEntity.getRecruter())
        .atempt(candidatEntity.getAtempt())
        .chatId(candidatEntity.getChatId())
        .comment(candidatEntity.getComment())
        .applicationDate(candidatEntity.getApplicationDate().format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm")))
        .build();
    }

    public CandidatEntityEditFormDto editEntityDto(CandidatEntity candidatEntity){
        return CandidatEntityEditFormDto.builder()
        .id(candidatEntity.getId())
        .name(candidatEntity.getName())
        .phoneNumber(candidatEntity.getPhoneNumber())
        .age(candidatEntity.getAge())
        .profession(candidatEntity.getProfession())
        .helthStatus(candidatEntity.getHelthStatus())
        .comment(candidatEntity.getComment())
        .build();
    }

    public CandidatEntity candidatEntityFromCandidatEntityFormDto(CandidatEntityEditFormDto formDto, CandidatEntity entityFromDatabase){
        CandidatEntity entity = entityFromDatabase;
        entity.setName(formDto.getName());
        entity.setPhoneNumber(formDto.getPhoneNumber());
        entity.setAge(formDto.getAge());
        entity.setProfession(formDto.getProfession());
        entity.setHelthStatus(formDto.getHelthStatus());
        entity.setComment(formDto.getComment());
        return entity;
    } 
}
