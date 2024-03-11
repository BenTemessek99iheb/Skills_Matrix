package com.elyadata.sm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Assessment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    private Boolean certified;
    private Boolean preferred;
    private Integer level;
    private String comment;
    private Instant createdAt;
    private String assessmentType;
    private String assessmentBy;
    private Boolean lastAssessment;

    @ManyToOne
    @JoinColumn(name = "employee_category_id")
    private EmployeeCategory employeeCategory;

    @ManyToOne
    private SessionAssessment sessionAssessment;
}
