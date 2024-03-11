package com.elyadata.sm.mapper;

import com.elyadata.sm.dto.SessionAssessmentDto;
import com.elyadata.sm.model.SessionAssessment;
import com.elyadata.sm.util.tools.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionAssessmentMapper extends GenericMapper<SessionAssessment, SessionAssessmentDto> {
}
