package com.elyadata.sm.service.impl;

import com.elyadata.sm.dto.SkillDTO;
import com.elyadata.sm.dto.kpis.SkillsPerCategory;
import com.elyadata.sm.dto.kpis.TopNMostRatedSkills;
import com.elyadata.sm.mapper.CategoryMapper;
import com.elyadata.sm.mapper.SkillMapper;
import com.elyadata.sm.model.Category;
import com.elyadata.sm.model.EmployeeCategory;
import com.elyadata.sm.model.Skill;
import com.elyadata.sm.repository.ICategoryRepo;
import com.elyadata.sm.repository.IEmployeeCategoryRepo;
import com.elyadata.sm.repository.ISkillRepo;
import com.elyadata.sm.service.ISkillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SkillService implements ISkillService {


    private final ISkillRepo skillRepository;
    private final SkillMapper skillMapper;
    private final CategoryMapper categoryMapper;
    private final ISkillRepo skillRepo;
    private final ICategoryRepo categoryRepo;
    private final IEmployeeCategoryRepo employeeCategoryRepo;


    public SkillDTO addSkill(SkillDTO skillDTO) {
        Skill skill = skillRepository.save(skillMapper.toEntity(skillDTO));
        return skillMapper.toDto(skill);
    }

    @Override
    public void removeSkill(UUID id) {
        skillRepository.deleteById(id);
    }

    @Override
    public SkillDTO retrieveSkill(UUID id) {
        return skillMapper.toDto(getSkillOrThrow(id));
    }

    @Override
    public Page<SkillDTO> retrieveAllSkills(Pageable pageable) {
        return skillRepository.findAll(pageable).map(skillMapper::toDto);
    }


    @Override
    public SkillDTO updateSkill(SkillDTO skillDTO) {

        Skill skill = getSkillOrThrow(skillDTO.getId());

        return skillMapper.toDto(skillRepository.save(skill));
    }

    private Skill getSkillOrThrow(UUID id) {
        return skillRepository.findById(id).orElseThrow(() -> new RuntimeException("Skill not found with id " + id));
    }

    //get skills by category
    public List<SkillDTO> getSkillsByCategory(String categoryName) {
        List<Skill> skills = skillRepository.findByCategoryName(categoryName);
        return skills.stream().map(skillMapper::toDto).collect(Collectors.toList());
    }


    public List<TopNMostRatedSkills> findTop10MostRatedSkills(Integer n) {
        return skillRepository.findTopNSkills(n);
    }

    @Override
    public SkillsPerCategory getSkillsPerCategory(UUID employeeId, UUID categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category with id %s not found ".formatted(categoryId)));

        List<Skill> skills = skillRepo.findSkillsPerCategoryId(employeeId, categoryId);

        return new SkillsPerCategory(categoryMapper.toDto(category), skills.stream().map(skillMapper::toDto).toList());

    }


    public Skill getNextSkillByCategoryOffset(UUID employeeId, int categoryOffset) {
        // Retrieve the next EmployeeCategory based on the employeeId and categoryOffset
        EmployeeCategory nextEmployeeCategory = employeeCategoryRepo.findNextByEmployeeIdAndCategoryId(employeeId, categoryOffset);

        if (nextEmployeeCategory == null) {
            throw new NotFoundException("No more categories available");
        }

        // Retrieve the skills associated with the next category
        List<Skill> skills = skillRepo.findByCategory(nextEmployeeCategory.getCategory());

        // Logic to determine the next skill within the category
        // This could be based on the current skill offset, user preferences, etc.
        Skill nextSkill = skills.get(0); // Example: get the first skill in the list

        return nextSkill;
    }

    @Override
    public List<SkillDTO> findSkillPerEmployee(UUID employeeId) {
        return (skillRepository.findSkillsByEmployeeId(employeeId)).stream().map(skillMapper::toDto).collect(Collectors.toList());
    }
    public long countSkills() {
        return skillRepo.count();
    }

}
