package com.elyadata.sm.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class SessionAssessment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private Instant createdAt;

    private Instant updatedAt;

    @ManyToOne
    private Employee assessedEmployee;

    @ManyToOne
    private Employee assessedBy;

    private Integer categoryOffset;

    @Enumerated(EnumType.STRING)
    private EAssessmentType assessmentType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sessionAssessment")
    private List<Assessment> assessments;

    private Boolean completed;


}
