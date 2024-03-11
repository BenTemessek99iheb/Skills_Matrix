package com.elyadata.sm.dto;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class EmployeeCategoryDTO {

    private UUID id;
    private EmployeeDto employee;
    private CategoryDTO category;
//    private Set<AssessmentDTO> assessments;
}
