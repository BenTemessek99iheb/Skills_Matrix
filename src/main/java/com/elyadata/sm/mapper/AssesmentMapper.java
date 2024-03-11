package com.elyadata.sm.mapper;

import com.elyadata.sm.dto.AssessmentDTO;
import com.elyadata.sm.model.Assessment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {SessionAssessmentMapper.class})
public interface AssesmentMapper {
    @Mapping(target = "sessionAssessmentId",source = "sessionAssessment.id")

    AssessmentDTO toDto(Assessment assessment);

    Assessment toEntity(AssessmentDTO assessmentDTO);

}
