package com.elyadata.sm.service;

import com.elyadata.sm.dto.SessionAssessmentDto;
import com.elyadata.sm.model.SessionAssessment;

import java.util.UUID;

public interface ISessionAssessmentService {

    // start session (fetch first employee category and set it as offset )
    SessionAssessmentDto save(SessionAssessmentDto sessionAssessmentDto);

    //  last
    SessionAssessmentDto getCurrentSessionAssessment();

    SessionAssessmentDto update(UUID id, SessionAssessmentDto sessionAssessmentDto);

    void delete(UUID id);
}
