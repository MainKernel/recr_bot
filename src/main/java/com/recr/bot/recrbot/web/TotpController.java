package com.recr.bot.recrbot.web;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.recr.bot.recrbot.model.service.TotpService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TotpController {
   

     private final TotpService totpService;

    @GetMapping("/totp")
    public ModelAndView totp(Model model, Principal principal) {
        ModelAndView mv = new ModelAndView("totp");
        String qrCode = totpService.imageForUser(principal.getName());
        System.out.println(qrCode);
        mv.addObject("qrCode", qrCode);
        return mv ;
    }
}
