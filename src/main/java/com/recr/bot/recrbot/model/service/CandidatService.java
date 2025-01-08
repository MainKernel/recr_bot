package com.recr.bot.recrbot.model.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.recr.bot.recrbot.model.dto.CandidatEntityEditFormDto;
import com.recr.bot.recrbot.model.dto.CandidatShortDto;
import com.recr.bot.recrbot.model.entity.CandidatEntity;
import com.recr.bot.recrbot.model.repository.CandidatRepository;
import com.recr.bot.recrbot.utils.CandidatUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandidatService {
    private final CandidatRepository candidatRepository;
    private final CandidatUtils candidatUtils;

    public void saveCandidateJobApplication(CandidatEntity candidatEntity) {

        try {
            List<CandidatEntity> byChatId = candidatRepository.findCandidatsByChatId(candidatEntity.getChatId());
            if (byChatId != null) {
                candidatEntity.setAtempt(byChatId.size() + 1);
            }
            // try to save candidate
            candidatRepository.save(candidatEntity);
        } catch (IllegalArgumentException | OptimisticLockingFailureException ex) {
            // loging exception
            log.error(ex.toString());
            // return 0 if some thing goes wrong
        }

    }

    public List<CandidatEntity> getNewCandidats() {
        return candidatRepository.findCandidatesWithoutRecruiter();
    }

    public List<CandidatEntity> getOldCandidatEntities() {
        return candidatRepository.findCandidatWithRecruiter();
    }

    public List<CandidatShortDto> getNewCandidatShortDtos() {
        return getNewCandidats().stream().map(n -> candidatUtils.EntityToShortDtoMapper(n))
                .collect(Collectors.toList());
    }

    public CandidatEntity getCandidatById(Long id) {
        return candidatRepository.getReferenceById(id);
    }

    public List<CandidatShortDto> getOldCandodatsShortDto() {
        return getOldCandidatEntities().stream().map(n -> candidatUtils.EntityToShortDtoMapper(n))
                .collect(Collectors.toList());
    }

    public int takeCandidat(long id, String recruiter) {
        System.out.println("Takeing candidat!");
        Optional<CandidatEntity> byId = candidatRepository.findById(id);
        if (byId.isPresent()) {
            CandidatEntity candidatEntity = byId.get();
            candidatEntity.setRecruter(recruiter);
            // Saveing candidat
            candidatRepository.save(candidatEntity);
            return 1;
        }
        return 0;
    }

    public CandidatEntityEditFormDto getFormDto(long id) {
        CandidatEntity candidatById = getCandidatById(id);
        return candidatUtils.editEntityDto(candidatById);
    }

    public int saveCandidatForm(CandidatEntityEditFormDto candidatEntityEditFormDto, long id) {

        CandidatEntity candidatById = getCandidatById(id);

        CandidatEntity candidatEntity = candidatUtils
                .candidatEntityFromCandidatEntityFormDto(candidatEntityEditFormDto, candidatById);

        try{
            candidatRepository.save(candidatEntity);
            return 1;

        } catch (IllegalArgumentException | OptimisticLockingFailureException ex) {
            return 0;
        }
    }
}