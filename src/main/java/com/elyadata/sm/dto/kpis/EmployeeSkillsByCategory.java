package com.elyadata.sm.dto.kpis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSkillsByCategory {
    private String category;
    private String skill;
    private Integer level;
}
