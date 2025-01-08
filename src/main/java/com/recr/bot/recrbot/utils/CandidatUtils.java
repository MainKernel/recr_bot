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
        .status(candidatEntity.getStatus().getStatus())
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
        .status(candidatEntity.getStatus().getStatus())
        .relationshipStatus(candidatEntity.getRelationshipStatus())
        .education(candidatEntity.getEducation().getTitle())
        .profession(candidatEntity.getProfession())
        .workExp(candidatEntity.getWorkExp())
        .militaryRank(candidatEntity.getMilitaryRank())
        .educationalInstitution(candidatEntity.getEducationalInstitution())
        .combatActionParticipant(candidatEntity.getCombatActionParticipant())
        .helthStatus(candidatEntity.getHelthStatus())
        .recruter(candidatEntity.getRecruter())
        .atempt(candidatEntity.getAtempt())
        .chatId(candidatEntity.getChatId())
        .branchOfService(candidatEntity.getBranchOfService())
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
        .status(candidatEntity.getStatus())
        .relationshipStatus(candidatEntity.getRelationshipStatus())
        .education(candidatEntity.getEducation())
        .profession(candidatEntity.getProfession())
        .workExp(candidatEntity.getWorkExp())
        .militaryRank(candidatEntity.getMilitaryRank())
        .educationalInstitution(candidatEntity.getEducationalInstitution())
        .combatActionParticipant(candidatEntity.getCombatActionParticipant())
        .helthStatus(candidatEntity.getHelthStatus())
        .branchOfService(candidatEntity.getBranchOfService())
        .comment(candidatEntity.getComment())
        .build();
    }

    public CandidatEntity candidatEntityFromCandidatEntityFormDto(CandidatEntityEditFormDto formDto, CandidatEntity entityFromDatabase){
        CandidatEntity entity = entityFromDatabase;
        entity.setName(formDto.getName());
        entity.setPhoneNumber(formDto.getPhoneNumber());
        entity.setAge(formDto.getAge());
        entity.setStatus(formDto.getStatus());
        entity.setRelationshipStatus(formDto.getRelationshipStatus());
        entity.setEducation(formDto.getEducation());
        entity.setProfession(formDto.getProfession());
        entity.setWorkExp(formDto.getWorkExp());
        entity.setMilitaryRank(formDto.getMilitaryRank());
        entity.setEducationalInstitution(formDto.getEducationalInstitution());
        entity.setCombatActionParticipant(formDto.getCombatActionParticipant());
        entity.setHelthStatus(formDto.getHelthStatus());
        entity.setBranchOfService(formDto.getBranchOfService());
        entity.setComment(formDto.getComment());
        return entity;
    } 
}
