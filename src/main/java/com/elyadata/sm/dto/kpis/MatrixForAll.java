package com.elyadata.sm.dto.kpis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MatrixForAll {
    private UUID employeeId  ;
    private String categoryName;
    private String skillName ;
    private Integer level ;
    private Boolean preferred ;

}
