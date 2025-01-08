package com.recr.bot.recrbot.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidatShortDto {
    private long id;
    private String name;
    private int age;
    private String status;
    private String profession;
    private int atempt;
    private String recruter;
}
