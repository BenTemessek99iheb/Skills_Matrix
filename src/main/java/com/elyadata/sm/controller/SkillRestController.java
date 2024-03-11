package com.elyadata.sm.controller;

import com.elyadata.sm.dto.SkillDTO;
import com.elyadata.sm.dto.kpis.SkillsPerCategory;
import com.elyadata.sm.model.Skill;
import com.elyadata.sm.repository.IAssessmentRepo;
import com.elyadata.sm.repository.ISkillRepo;
import com.elyadata.sm.service.ISkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Skills Managment")
@RestController
@RequestMapping("/api/v1/skills")

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@ControllerAdvice
public class SkillRestController {
    private final ISkillService skillService;

    @Operation(description = "Retrieve all users pagable")
    @GetMapping
    public ResponseEntity<Page<SkillDTO>> getSkills(Pageable pageable) {
        return ResponseEntity.ok(skillService.retrieveAllSkills(pageable));
    }

    @Operation(description = "add new skill ")
    @PostMapping()
    public SkillDTO addSkill(@Valid @RequestBody SkillDTO s) {

        return skillService.addSkill(s);
    }

    @Operation(description = "Remove a skill by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeSkill(@PathVariable UUID id) {
        skillService.removeSkill(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Update a skill")
    @PutMapping
    public ResponseEntity<SkillDTO> updateSkill(@RequestBody SkillDTO skillDTO) {

        return ResponseEntity.ok(skillService.updateSkill(skillDTO));
    }

    @Operation(description = "Retrieve a skill by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> getSkill(@PathVariable UUID id) {
        return ResponseEntity.ok(skillService.retrieveSkill(id));
    }

    @Operation(description = "Retrieve skills by their Category")
    @GetMapping("/category/{name}")
    public ResponseEntity<List<SkillDTO>> getSkillsByCategory(@PathVariable String name) {
        return ResponseEntity.ok(skillService.getSkillsByCategory(name));
    }

    @GetMapping("/categories/{categoryId}/employees/{employeeId}")
    public SkillsPerCategory EmployeeSkillsPerCategory(@PathVariable UUID employeeId, @PathVariable UUID categoryId) {
        return skillService.getSkillsPerCategory(employeeId, categoryId);
    }

    @GetMapping("/skillsPerEmployee/{employeeId}")
    public List<SkillDTO> findSkillPerEmployee(@RequestParam UUID employeeId) {
        return skillService.findSkillPerEmployee(employeeId);
    }

    @GetMapping("/count")
    public Long countSkills() {
        return skillService.countSkills();
    }

}
