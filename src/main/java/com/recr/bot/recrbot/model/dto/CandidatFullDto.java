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
    private String profession;
    private String helthStatus;
    private String recruter;
    private int atempt;
    private Long chatId;
    private String comment;
    private String applicationDate;
}
