package com.elyadata.sm.service;

import com.elyadata.sm.dto.SkillDTO;
import com.elyadata.sm.dto.kpis.SkillsPerCategory;
import com.elyadata.sm.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface ISkillService {
    SkillDTO addSkill(SkillDTO skillDTO);

    void removeSkill(UUID id);

    SkillDTO retrieveSkill(UUID id);

    Page<SkillDTO> retrieveAllSkills(Pageable pageable);

    List<SkillDTO> getSkillsByCategory(String categoryName);

    SkillDTO updateSkill(SkillDTO skillDTO);//TODO TEMPORARY METHOD AND REPLACE IT WITH public SkillDTO updateSkill(UUID id,SkillDTO skillDTO)

    SkillsPerCategory getSkillsPerCategory(UUID employeeId, UUID categoryId);

    List<SkillDTO> findSkillPerEmployee(UUID employeeId);

    long countSkills();
}
