package com.elyadata.sm.dto.kpis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSkillsMatrix {
    private UUID employeeId;
    private SkillsMatrix skillsMatrix;
}
