package com.elyadata.sm.controller;

import com.elyadata.sm.dto.SessionAssessmentDto;
import com.elyadata.sm.model.Assessment;
import com.elyadata.sm.model.SessionAssessment;
import com.elyadata.sm.service.impl.SessionAssessmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Session Assesment Managment")
@RestController
@RequestMapping("/api/v1/session-assessments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SessionAssesmentRestController {
    private final SessionAssessmentService sessionAssessmentService;

    @PostMapping
    public SessionAssessmentDto save(@RequestBody SessionAssessmentDto sessionAssessmentDto) {
        return sessionAssessmentService.save(sessionAssessmentDto);
    }

    @PutMapping("/{id}")
    public SessionAssessmentDto update(@PathVariable UUID id, @RequestBody SessionAssessmentDto sessionAssessmentDto) {
        return sessionAssessmentService.update(id, sessionAssessmentDto);
    }


    @GetMapping("/{id}/next-assessment")
    public Assessment getNextAssessment(@PathVariable UUID id) {
        return sessionAssessmentService.getNextAssessment(id);
    }
    @PostMapping("/findOrCreateSessionForEmployee/{idEmployee}")
    public SessionAssessmentDto findOrCreateSessionForEmployee(@PathVariable UUID idEmployee) {
        return sessionAssessmentService.findOrCreateSessionForEmployee(idEmployee);
    }
    @GetMapping("/{id}")
    public SessionAssessmentDto findById(@PathVariable UUID id) {
        return sessionAssessmentService.findSessionById(id);
    }


}
