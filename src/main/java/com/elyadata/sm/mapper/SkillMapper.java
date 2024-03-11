package com.elyadata.sm.mapper;

import com.elyadata.sm.dto.SkillDTO;
import com.elyadata.sm.model.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {CategoryMapper.class})

public interface SkillMapper {
@Mapping(target = "categoryId",source = "category.id")
    SkillDTO toDto(Skill skill);

    Skill toEntity(SkillDTO skillDTO);

}
