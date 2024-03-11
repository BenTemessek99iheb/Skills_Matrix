package com.elyadata.sm.service.impl;

import com.elyadata.sm.dto.AssessmentDTO;
import com.elyadata.sm.dto.kpis.*;
import com.elyadata.sm.mapper.AssesmentMapper;
import com.elyadata.sm.model.Assessment;
import com.elyadata.sm.model.SessionAssessment;
import com.elyadata.sm.repository.IAssessmentRepo;
import com.elyadata.sm.repository.ISessionAssessmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssessmentService {

    private final IAssessmentRepo assessmentRepository;
    private final AssesmentMapper assesmentMapper;
    private final ISessionAssessmentRepo sessionAssessmentRepo;

    public SkillsMatrix getSkillsMatrix(UUID employeeId) {
        List<EmployeeSkillsByCategory> results = assessmentRepository.getEmployeeSkillsByCategory(employeeId);

        SkillsMatrix skillsMatrix = new SkillsMatrix(new HashMap<>(), new HashMap<>());

        for (EmployeeSkillsByCategory result : results) {
            skillsMatrix.addEntryWithoutPreferred(result.getCategory(), result.getSkill(), result.getLevel());
        }

        return skillsMatrix;
    }

    public AssessmentDTO addAssesment(AssessmentDTO assessmentDTO) {

        return assesmentMapper.toDto(assessmentRepository.save(assesmentMapper.toEntity(assessmentDTO)));
    }

    public List<AssessmentDTO> addAssessments(List<AssessmentDTO> assessmentDTOs) {
        List<AssessmentDTO> savedAssessments = new ArrayList<>();
        for (AssessmentDTO assessmentDTO : assessmentDTOs) {
            Assessment savedAssessment = assessmentRepository.save(assesmentMapper.toEntity(assessmentDTO));
            savedAssessments.add(assesmentMapper.toDto(savedAssessment));
        }

        return savedAssessments;
    }

    public Assessment saveAssessmentWithinSession(UUID sessionAssessmentId, Assessment assessment) {
        SessionAssessment sessionAssessment = sessionAssessmentRepo.findById(sessionAssessmentId)
                .orElseThrow(() -> new NotFoundException("SessionAssessment not found"));

        assessment.setSessionAssessment(sessionAssessment);
        return assessmentRepository.save(assessment);
    }

    public List<EmployeeSkillsMatrix> getAllSkillsMatrices() {
        List<MatrixForAll> results = assessmentRepository.getAllEmployeeSkillsByCategory();

        Map<UUID, SkillsMatrix> matricesMap = new HashMap<>();

        for (MatrixForAll result : results) {
            matricesMap
                    .computeIfAbsent(result.getEmployeeId(), k -> new SkillsMatrix(new HashMap<>(), new HashMap<>()))
                    .addEntry(result.getCategoryName(), result.getSkillName(), result.getLevel(), result.getPreferred());
        }

        List<EmployeeSkillsMatrix> matricesList = new ArrayList<>();
        for (Map.Entry<UUID, SkillsMatrix> entry : matricesMap.entrySet()) {
            matricesList.add(new EmployeeSkillsMatrix(entry.getKey(), entry.getValue()));
        }

        return matricesList;
    }

    //get details of the assesment of an employee
    public List<AssesmentDetails> getAssesmentsDetailsHistory(UUID employeeId) {
        List<AssesmentDetails> assessments = assessmentRepository.findAllAssessmentsByEmployeeIdOrderedByDate(employeeId);
        return assessments.stream()// Convert each Assessment to AssessmentDTO
                .collect(Collectors.toList());
    }

}
