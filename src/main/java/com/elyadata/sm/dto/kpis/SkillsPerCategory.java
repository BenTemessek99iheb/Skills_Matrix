package com.elyadata.sm.dto.kpis;

import com.elyadata.sm.dto.CategoryDTO;
import com.elyadata.sm.dto.SkillDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SkillsPerCategory {
    CategoryDTO category;
    List<SkillDTO> skills;


}
