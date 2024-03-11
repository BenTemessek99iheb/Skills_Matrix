package com.elyadata.sm.dto.kpis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SkillPreferenceDTO {
    private String skillName;
    private double preferenceRate;

}
