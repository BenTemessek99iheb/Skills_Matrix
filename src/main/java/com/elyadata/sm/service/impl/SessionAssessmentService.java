package com.elyadata.sm.service.impl;

import com.elyadata.sm.dto.EmployeeCategoryDTO;
import com.elyadata.sm.dto.SessionAssessmentDto;
import com.elyadata.sm.mapper.SessionAssessmentMapper;
import com.elyadata.sm.model.Assessment;
import com.elyadata.sm.model.SessionAssessment;
import com.elyadata.sm.model.Skill;
import com.elyadata.sm.repository.IEmployeeRepo;
import com.elyadata.sm.repository.ISessionAssessmentRepo;
import com.elyadata.sm.service.ISessionAssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionAssessmentService implements ISessionAssessmentService {

    private final ISessionAssessmentRepo sessionAssessmentRepo;
    private final SessionAssessmentMapper sessionAssessmentMapper;
    private final EmployeeCategoryService employeeCategoryService;
    private final SkillService skillService;
    private final IEmployeeRepo employeeRepo;


    @Override
    public SessionAssessmentDto save(SessionAssessmentDto sessionAssessmentDto) {
        sessionAssessmentDto.setCategoryOffset(0);
        SessionAssessment sessionAssessment = sessionAssessmentRepo.save(sessionAssessmentMapper.toModel(sessionAssessmentDto));
        return sessionAssessmentMapper.toDto(sessionAssessment);
    }

    @Override
    public SessionAssessmentDto getCurrentSessionAssessment() {
        return null;
    }

    @Override
    public SessionAssessmentDto update(UUID id, SessionAssessmentDto sessionAssessmentDto) {

        EmployeeCategoryDTO nextEmployeeCategory = employeeCategoryService.getNextEmployeeCategoryByEmployeeId(sessionAssessmentDto.getAssessedEmployee().getId(), sessionAssessmentDto.getCategoryOffset() + 1);
        if (nextEmployeeCategory == null) {
            sessionAssessmentDto.setCompleted(true);
        }
        sessionAssessmentDto.setCategoryOffset(sessionAssessmentDto.getCategoryOffset() + 1);
        sessionAssessmentRepo.save(sessionAssessmentMapper.toModel(sessionAssessmentDto));

        return sessionAssessmentDto;
    }

    @Override
    public void delete(UUID id) {

    }

    public Assessment getNextAssessment(UUID sessionAssessmentId) {
        SessionAssessment sessionAssessment = sessionAssessmentRepo.findById(sessionAssessmentId)
                .orElseThrow(() -> new NotFoundException("SessionAssessment not found"));

        int categoryOffset = sessionAssessment.getCategoryOffset();
        UUID employeeId = sessionAssessment.getAssessedEmployee().getId();

        // to retrieve the next skill based on the category offset
        Skill nextSkill = skillService.getNextSkillByCategoryOffset(employeeId, categoryOffset);

        // Create a new Assessment object for the next skill
        Assessment nextAssessment = new Assessment();
        nextAssessment.setSkill(nextSkill);
        nextAssessment.setSessionAssessment(sessionAssessment);

        return nextAssessment;
    }
    public SessionAssessmentDto findOrCreateSessionForEmployee(UUID employeeId) {
        SessionAssessment session = sessionAssessmentRepo.findByAssessedEmployee(employeeId).orElse(null);

        if (session == null) {
            session = new SessionAssessment();
            // Initialize other fields of session if necessary
            session.setAssessedEmployee(employeeRepo.findById(employeeId).orElse(null));
            sessionAssessmentRepo.save(session);

        }
        return  sessionAssessmentMapper.toDto(session);
    }
    //find session by id
    public SessionAssessmentDto findSessionById(UUID id) {
        SessionAssessment session = sessionAssessmentRepo.findById(id).orElse(null);
        return sessionAssessmentMapper.toDto(session);
    }
}
