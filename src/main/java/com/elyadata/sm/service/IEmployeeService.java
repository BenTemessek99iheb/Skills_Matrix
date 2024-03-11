package com.elyadata.sm.service;

import com.elyadata.sm.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface IEmployeeService {
    EmployeeDto addUser(EmployeeDto u);

    void removeUser(UUID id);

    Page<EmployeeDto> retrieveAllUsers(Pageable pageable);
    public List<EmployeeDto> retrieveUsers();

    EmployeeDto updateUser(EmployeeDto employeeDto);


    EmployeeDto retrieveUserById(UUID id);

    Long countUsers();

}
