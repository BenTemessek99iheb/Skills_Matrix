package com.elyadata.sm.dto.kpis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssesmentDetails {
    private String skillName ;
    private Integer level ;
    private Boolean preferred ;
    private Boolean certified ;


}
