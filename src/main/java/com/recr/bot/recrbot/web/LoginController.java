package com.recr.bot.recrbot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

}
