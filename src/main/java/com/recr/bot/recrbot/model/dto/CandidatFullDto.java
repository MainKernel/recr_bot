package com.recr.bot.recrbot.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidatFullDto {
 
    private long id;
    private String name;
    private String phoneNumber;
    private int age;
    private String status;
    private String relationshipStatus;
    private String education;
    private String profession;
    private String workExp;
    private String militaryRank;
    private String educationalInstitution;
    private String combatActionParticipant;
    private String helthStatus;
    private String recruter;
    private int atempt;
    private Long chatId;
    private String branchOfService;
    private String comment;
    private String applicationDate;
}
