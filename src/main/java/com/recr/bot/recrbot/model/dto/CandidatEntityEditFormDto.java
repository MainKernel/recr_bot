package com.recr.bot.recrbot.model.dto;

import com.recr.bot.recrbot.model.entity.enums.Education;
import com.recr.bot.recrbot.model.entity.enums.MilitaryStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidatEntityEditFormDto {
    
    private long id;
    private String name;
    private String phoneNumber;
    private int age;
    private MilitaryStatus status;
    private String relationshipStatus;
    private Education education;
    private String profession;
    private String workExp;
    private String militaryRank;
    private String educationalInstitution;
    private String combatActionParticipant;
    private String helthStatus;
    private String branchOfService;
    private String comment;
}
