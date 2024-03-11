package com.elyadata.sm.mapper;

import com.elyadata.sm.dto.EmployeeDto;
import com.elyadata.sm.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    Employee toEntity(EmployeeDto employeeDto);
}
