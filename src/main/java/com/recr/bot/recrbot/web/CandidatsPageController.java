package com.recr.bot.recrbot.web;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.recr.bot.recrbot.model.dto.CandidatEntityEditFormDto;
import com.recr.bot.recrbot.model.dto.CandidatFullDto;
import com.recr.bot.recrbot.model.dto.CandidatShortDto;
import com.recr.bot.recrbot.model.service.CandidatService;
import com.recr.bot.recrbot.utils.CandidatUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CandidatsPageController {
    private final CandidatService candidatService;
    private final CandidatUtils utils;

    @GetMapping("/candidats/new")
    public ModelAndView getCandidatsPage() {
        ModelAndView model = new ModelAndView("candidats-new");
        List<CandidatShortDto> newCandidatShortDtos = candidatService.getNewCandidatShortDtos();
        model.addObject("candidates", newCandidatShortDtos);

        return model;
    }

    @GetMapping("/candidats/old")
    public ModelAndView getOldCandidats() {
        ModelAndView model = new ModelAndView("candidats-old");
        List<CandidatShortDto> oldCandidatEntities = candidatService.getOldCandodatsShortDto();
        model.addObject("candidates", oldCandidatEntities);
        return model;
    }

    @GetMapping("/candidats/take/{id}")
    public String takeCandidat(@PathVariable("id") long candidatId, Principal principal) {

        candidatService.takeCandidat(candidatId, principal.getName());

        return "redirect:/candidats/new";
    }

    @GetMapping("/candidats/edit/{id}")
    public ModelAndView editCandidat(@PathVariable("id") long candidatId) {
        ModelAndView mv = new ModelAndView("candidats-edit");

        CandidatEntityEditFormDto formDto = candidatService.getFormDto(candidatId);

        mv.addObject("candidatEntity", formDto);

        return mv;
    }

    @PostMapping("/candidats/edit/{id}")
    public String updateCandidat(@ModelAttribute("candidat") CandidatEntityEditFormDto form,
            @PathVariable("id") long id) {
        String redirectUrl = "redirect:/candidats/" + id;
        candidatService.saveCandidatForm(form, id);

        return redirectUrl;
    }

    @GetMapping("/candidats/{id}")
    public ModelAndView getCandidatProfile(@PathVariable("id") long candidatId) {
        ModelAndView mv = new ModelAndView("candidate-profile");

        CandidatFullDto candidat = utils.EntityToFullDtoMapper(candidatService.getCandidatById(candidatId));

        mv.addObject("candidat", candidat);
        return mv;
    }

}
