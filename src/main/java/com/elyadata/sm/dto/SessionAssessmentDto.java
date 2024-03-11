package com.elyadata.sm.dto;

import com.elyadata.sm.model.Assessment;
import com.elyadata.sm.model.EAssessmentType;
import com.elyadata.sm.model.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SessionAssessmentDto {

    private UUID id;

    private Instant createdAt;

    private Instant updatedAt;

    private EmployeeDto assessedEmployee;

    private EmployeeDto assessedBy;

    private EAssessmentType assessmentType;

    private List<AssessmentDTO> assessments;

    private Integer categoryOffset;

    private Boolean completed;
}
