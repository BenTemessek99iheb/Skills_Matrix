package com.elyadata.sm.controller;

import com.elyadata.sm.dto.CategoryDTO;
import com.elyadata.sm.dto.EmployeeCategoryDTO;
import com.elyadata.sm.dto.kpis.PercentageEmployeePerCategory;
import com.elyadata.sm.service.impl.EmployeeCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Employee Managment")
@RestController
@RequestMapping("/api/v1/employee-category")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeCategoryRestController {
    private final EmployeeCategoryService employeeCategoryService;

    @GetMapping("/count")
    public List<PercentageEmployeePerCategory> countSkills() {
        return employeeCategoryService.countPercentageEmployeesPerCategory();
    }

    @GetMapping("/findEmployeeCategoryByCategory/{categoryId}/Employee/{employeeId}")
    public EmployeeCategoryDTO findEmployeeCategoryByCategoryAndEmployee(@PathVariable UUID categoryId, @PathVariable UUID employeeId) {
        return employeeCategoryService.findEmployeeCategoryByCategoryAndEmployee(categoryId, employeeId);
    }
    @GetMapping("/findEmployeeCategory/{categoryOffset}/categoryOffsetByEmployee/{employeeId}")
    public EmployeeCategoryDTO findNextByEmployeeIdAndCategory(@PathVariable UUID employeeId, @PathVariable Integer categoryOffset) {
        return employeeCategoryService.findNextByEmployeeIdAndCategoryId(employeeId, categoryOffset);
    }
  //  findCategoriesByEmployeeId
    @GetMapping("/findCategoriesByEmployeeId/{employeeId}")
public List<CategoryDTO> findCategoriesByEmployeeId(@PathVariable UUID employeeId) {
        return employeeCategoryService.findCategoriesByEmployeeId(employeeId);
    }
}
