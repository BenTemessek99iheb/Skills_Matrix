package com.elyadata.sm.service.impl;

import com.elyadata.sm.service.IEmployeeService;
import com.elyadata.sm.util.constant.MessageConstant;
import com.elyadata.sm.util.supplier.ExceptionSupplier;
import com.elyadata.sm.dto.EmployeeDto;
import com.elyadata.sm.mapper.EmployeeMapper;
import com.elyadata.sm.model.Employee;
import com.elyadata.sm.repository.IEmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepo iEmployeeRepo;
    private final EmployeeMapper employeeMapper;

    public EmployeeDto addUser(EmployeeDto employeeDto) {

        return employeeMapper.toDto(iEmployeeRepo.save(employeeMapper.toEntity(employeeDto)));
    }

    public List<EmployeeDto> retrieveUsers() {
        return iEmployeeRepo.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }


    public void removeUser(UUID id) {
        iEmployeeRepo.deleteById(id);
    }


    @Override
    public Page<EmployeeDto> retrieveAllUsers(Pageable pageable) {
        return iEmployeeRepo.findAll(pageable).map(employeeMapper::toDto);
    }

    @Override
    public EmployeeDto updateUser(EmployeeDto employeeDto) {
        getEmployeeOrThrowException(employeeDto.getId());
        iEmployeeRepo.save(employeeMapper.toEntity(employeeDto));
        return employeeDto;
    }


    @Override
    public EmployeeDto retrieveUserById(UUID id) {
        return employeeMapper.toDto(getEmployeeOrThrowException(id));
    }

    private Employee getEmployeeOrThrowException(UUID id) {
        return iEmployeeRepo.findById(id)
                .orElseThrow(
                        ExceptionSupplier.getNotFoundExceptionSupplier(String.valueOf(id),
                                MessageConstant.ENTITY_NOT_FOUND));
    }

    public Long countUsers() {
        return iEmployeeRepo.count();
    }
}
