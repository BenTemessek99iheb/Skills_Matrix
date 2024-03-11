package com.elyadata.sm.service.impl;

import com.elyadata.sm.dto.EmployeeCategoryDTO;
import com.elyadata.sm.dto.SessionAssessmentDto;
import com.elyadata.sm.mapper.SessionAssessmentMapper;
import com.elyadata.sm.model.Employee;
import com.elyadata.sm.model.SessionAssessment;
import com.elyadata.sm.repository.IEmployeeRepo;
import com.elyadata.sm.repository.ISessionAssessmentRepo;
import com.elyadata.sm.service.ISessionAssessmentService;
import jakarta.persistence.SecondaryTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ISessionAssessmentServiceImpl implements ISessionAssessmentService {

    private final ISessionAssessmentRepo sessionAssessmentRepo;
    private final SessionAssessmentMapper sessionAssessmentMapper;
    private final EmployeeCategoryService employeeCategoryService;


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


        EmployeeCategoryDTO nextEmployeeCategory = employeeCategoryService.getNextEmployeeCategoryByEmployeeId(sessionAssessmentDto.getAssessedBy().getId(), sessionAssessmentDto.getCategoryOffset() + 1);
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


}
