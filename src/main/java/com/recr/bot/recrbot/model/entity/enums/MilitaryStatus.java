package com.recr.bot.recrbot.model.entity.enums;

public enum MilitaryStatus {
    onDuty("Військовослужбовець"),
    leftDuty("СЗЧ"),
    civilian("Цивільний");

    private String status;

    MilitaryStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}