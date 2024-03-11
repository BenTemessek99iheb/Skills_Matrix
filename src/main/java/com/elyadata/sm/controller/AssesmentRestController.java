package com.elyadata.sm.controller;

import com.elyadata.sm.dto.AssessmentDTO;
import com.elyadata.sm.dto.kpis.*;
import com.elyadata.sm.model.Assessment;
import com.elyadata.sm.repository.IAssessmentRepo;
import com.elyadata.sm.service.impl.AssessmentService;
import com.elyadata.sm.service.impl.SkillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Assesment Managment")
@RestController
@RequestMapping("/api/v1/Assesments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AssesmentRestController {

    private final SkillService skillService;
    private final IAssessmentRepo assessmentRepo;
    private final AssessmentService assessmentService;

    @GetMapping("/top/{n}")
    public List<TopNMostRatedSkills> getTop10MostRatedSkills(@PathVariable Integer n) {
        return skillService.findTop10MostRatedSkills(n);
    }

    @GetMapping("/avg-preference")
    public List<SkillPreferenceDTO> AverageSkillPreferences() {
        return assessmentRepo.findAverageSkillPreferences();
    }

    @GetMapping("/avg-preference/{userId}")
    public List<SkillPreferenceDTO> AverageSkillPreferencesById(@PathVariable UUID userId) {
        return assessmentRepo.findAverageSkillPreferencesById(userId);
    }


    @GetMapping("/matrix/{id}")
    public SkillsMatrix getEmployeeSkillsByCategory(@PathVariable UUID id) {
        return assessmentService.getSkillsMatrix(id);
        // FIXME: 07/08/2023 removing The map  and use DTO INSTEAD within getSkillsMatrix
    }

    @GetMapping("/avg-skill-by-category-matrix/{id}")
    public List<AverageSkillLevelPerCategory> averageSkillLevelPerCategory(@PathVariable UUID id) {
        return assessmentRepo.averageSkillLevelPerCategory(id);
    }

    @GetMapping("/most-rated-skills/{id}")
    public List<TopNMostRatedSkills> findTopNSkillsByEmployeeId(@PathVariable UUID id) {
        return assessmentRepo.findTopSkillsByEmployeeId(id);
    }

    @PostMapping
    public AssessmentDTO addAssesment(@RequestBody AssessmentDTO a) {
        return assessmentService.addAssesment(a);
    }

    @PostMapping("ListOfAssesments")
    public List<AssessmentDTO> addAssessments(@RequestBody List<AssessmentDTO> assessments) {
        return assessmentService.addAssessments(assessments);
    }
    @GetMapping("/all-matrices")
    public List<EmployeeSkillsMatrix> getAllEmployeeSkillsMatrices() {
        return assessmentService.getAllSkillsMatrices();
    }
    @GetMapping("/DetailsByEmplyee/{employeeId}")
    public List<AssesmentDetails> getAssesmentsDetailsHistoryByEmployeeId(@PathVariable UUID employeeId) {
        return assessmentService.getAssesmentsDetailsHistory(employeeId);
    }
    //averageSkillLevelPerCategoryForAll
    @GetMapping("/avg-skill-by-category-matrix")
    public List<AverageSkillLevelPerCategory> averageSkillLevelPerCategoryForAll() {
        return assessmentRepo.averageSkillLevelPerCategoryForAll();
    }
}
