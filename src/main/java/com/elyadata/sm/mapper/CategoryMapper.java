package com.elyadata.sm.mapper;

import com.elyadata.sm.dto.CategoryDTO;
import com.elyadata.sm.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDto(Category category);

    Category toEntity(CategoryDTO categoryDTO);

}
