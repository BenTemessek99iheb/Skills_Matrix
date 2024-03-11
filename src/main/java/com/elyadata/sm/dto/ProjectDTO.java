package com.elyadata.sm.dto;

import com.elyadata.sm.model.Employee;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class ProjectDTO {

    private UUID id;
    private String name;
    private String description;
    private Instant startDate;
    private Instant endDate;
    private Set<EmployeeDto> employees;

}
