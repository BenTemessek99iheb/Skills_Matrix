package com.elyadata.sm.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class AssessmentDTO {

    private UUID id;
    private SkillDTO skill;
    private Boolean certified;
    private Boolean preferred;
    private Integer level;
    private EmployeeCategoryDTO employeeCategory;
    private String comment;
    private Instant createdAt;
    private String assessmentType;
    private String assessmentBy;
    private Boolean lastAssessment;
    private UUID sessionAssessmentId;

}
