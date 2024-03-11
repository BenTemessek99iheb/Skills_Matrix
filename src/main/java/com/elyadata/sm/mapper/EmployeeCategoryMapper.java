package com.elyadata.sm.mapper;

import com.elyadata.sm.dto.EmployeeCategoryDTO;
import com.elyadata.sm.model.EmployeeCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeCategoryMapper {

    EmployeeCategoryDTO toDto(EmployeeCategory employee_category);

    EmployeeCategory toEntity(EmployeeCategoryDTO employee_categoryDTO);

}
