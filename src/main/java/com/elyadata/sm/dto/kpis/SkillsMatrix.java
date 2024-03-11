package com.elyadata.sm.dto.kpis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkillsMatrix {
    private Map<String, Map<String, List<Integer>>> matrix;
    private Map<String, Map<String, List<SkillDetail>>> matrixWithPreference;


    public void addEntry(String category, String skill, Integer level, Boolean preferred) {
        if (!matrixWithPreference.containsKey(category)) {
            matrixWithPreference.put(category, new HashMap<>());
        }

        Map<String, List<SkillDetail>> skills = matrixWithPreference.get(category);

        if (!skills.containsKey(skill)) {
            skills.put(skill, new ArrayList<>());
        }

        skills.get(skill).add(new SkillDetail(level, preferred));
    }
    public void addEntryWithoutPreferred(String category, String skill, Integer level) {
        if (!matrix.containsKey(category)) {
            matrix.put(category, new HashMap<>());
        }

        Map<String, List<Integer>> skills = matrix.get(category);

        if (!skills.containsKey(skill)) {
            skills.put(skill, new ArrayList<>());
        }

        skills.get(skill).add(level);
    }

}
