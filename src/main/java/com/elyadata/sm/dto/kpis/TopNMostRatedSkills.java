package com.elyadata.sm.dto.kpis;

import lombok.*;

import java.util.UUID;

@Value
@AllArgsConstructor
public class TopNMostRatedSkills {


    private UUID skillId;
    private String skillName;
    private Double level;

}
