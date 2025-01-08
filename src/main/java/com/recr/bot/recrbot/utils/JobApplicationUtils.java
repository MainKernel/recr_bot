package com.recr.bot.recrbot.utils;

import org.springframework.stereotype.Component;

@Component
public class JobApplicationUtils {

    public String branchMapper(String brunch) {
        String mappedBrunch;
        switch (brunch) {
            case "tro":
                mappedBrunch = "ТРО";
                break;
            case "sso":
                mappedBrunch = "ССО";
                break;
            case "gur":
                mappedBrunch = "ГУР";
                break;
            case "dsv":
                mappedBrunch = "ДШВ";
                break;
            case "mp":
                mappedBrunch = "МП";
                break;
            case "sv":
                mappedBrunch = "СВ";
                break;
            case "vms":
                mappedBrunch = "ВМС";
                break;
            case "ngu":
                mappedBrunch = "НГУ";
                break;
            case "dpsu":
                mappedBrunch = "ДПСУ";
                break;
            case "ps":
                mappedBrunch = "ПС";
                break;
            case "gh":
                mappedBrunch = "ГШ";
                break;
            case "mo":
                mappedBrunch = "МО";
                break;
            case "cbs":
                mappedBrunch = "СБС";
                break;
            case "sbu":
                mappedBrunch = "СБУ";
                break;
            default:
                mappedBrunch = "Виникла помилка під час обробки данних";
                break;
        }

        return mappedBrunch;
    }

}
