package com.recr.bot.recrbot.model.entity.enums;

public enum Education {
    SPECIAL("Середня спеціальна"),
    MEDIUM("Середня"),
    HIGHER("Вища");

    private String title;

    Education(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
