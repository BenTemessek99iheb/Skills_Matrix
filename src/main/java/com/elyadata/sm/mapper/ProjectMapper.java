package com.elyadata.sm.mapper;

import com.elyadata.sm.dto.ProjectDTO;
import com.elyadata.sm.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO toDto(Project project);

    Project toEntity(ProjectDTO projectDTO);

}
