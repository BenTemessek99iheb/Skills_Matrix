package com.elyadata.sm.service.impl;

import com.elyadata.sm.dto.CategoryDTO;
import com.elyadata.sm.dto.EmployeeCategoryDTO;
import com.elyadata.sm.dto.kpis.CountEmployeePerCategory;
import com.elyadata.sm.dto.kpis.PercentageEmployeePerCategory;
import com.elyadata.sm.mapper.CategoryMapper;
import com.elyadata.sm.mapper.EmployeeCategoryMapper;
import com.elyadata.sm.model.Category;
import com.elyadata.sm.model.EmployeeCategory;
import com.elyadata.sm.repository.IEmployeeCategoryRepo;
import com.elyadata.sm.repository.IEmployeeRepo;
import com.elyadata.sm.service.IEmployeeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeCategoryService implements IEmployeeCategoryService {

    private final IEmployeeCategoryRepo employeeCategoryRepository;
    private final IEmployeeRepo employeeRepository;
    private final EmployeeCategoryMapper employeeCategoryMapper;
private final CategoryMapper categoryMapper;
    public List<PercentageEmployeePerCategory> countPercentageEmployeesPerCategory() {
        long countEmployee = employeeRepository.count();
        List<CountEmployeePerCategory> percentageOfEmployeesByCategory = employeeCategoryRepository.findPercentageOfEmployeesByCategory();
        return percentageOfEmployeesByCategory.stream().map(categoryCount -> new PercentageEmployeePerCategory(categoryCount.CategoryName(), categoryCount.countEmployeePerCategory() * 100 / countEmployee)).collect(Collectors.toList());
    }

    public EmployeeCategoryDTO getNextEmployeeCategoryByEmployeeId(UUID employeeId, int categoryOffset) {

        return employeeCategoryMapper.toDto(employeeCategoryRepository.findNextByEmployeeIdAndCategoryId(employeeId, categoryOffset));

    }

    public EmployeeCategoryDTO findEmployeeCategoryByCategoryAndEmployee(UUID categoryId, UUID employeeId) {
        EmployeeCategory employeeCategory = employeeCategoryRepository.findByCategoryAndEmployee(categoryId, employeeId);
        return employeeCategoryMapper.toDto(employeeCategory);
    }
    public EmployeeCategoryDTO findNextByEmployeeIdAndCategoryId(UUID employeeId, Integer categoryOffset){
        EmployeeCategory employeeCategory= employeeCategoryRepository.findNextByEmployeeIdAndCategoryId(employeeId, categoryOffset);
    return employeeCategoryMapper.toDto(employeeCategory);
    }
    public List<CategoryDTO> findCategoriesByEmployeeId(UUID employeeId){
        List<Category> categories= employeeCategoryRepository.findCategoriesByEmployeeId(employeeId);
        return categories.stream().map(categoryMapper::toDto).toList();
    }
}
