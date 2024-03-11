package com.elyadata.sm.repository;

import com.elyadata.sm.model.Category;
import com.elyadata.sm.model.EmployeeCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.elyadata.sm.dto.kpis.CountEmployeePerCategory;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface IEmployeeCategoryRepo extends JpaRepository<EmployeeCategory, UUID> {
    @Query("SELECT new com.elyadata.sm.dto.kpis.CountEmployeePerCategory (c.name , Count(ec.employee) )" +
            " FROM EmployeeCategory ec JOIN ec.category c GROUP BY c.name")
    List<CountEmployeePerCategory> findPercentageOfEmployeesByCategory();

    @Query(value = "select * from employee_category e where e.employee_id= :employeeId order by e.created_at offset :categoryOffset limit 1 ", nativeQuery = true)
    EmployeeCategory findNextByEmployeeIdAndCategoryId(UUID employeeId, Integer categoryOffset);

    @Query("SELECT ec FROM EmployeeCategory ec WHERE ec.category.id = :categoryId AND ec.employee.id = :employeeId")
    EmployeeCategory findByCategoryAndEmployee(@Param("categoryId") UUID categoryId, @Param("employeeId") UUID employeeId);
    @Query("SELECT c FROM EmployeeCategory ec JOIN ec.category c WHERE ec.employee.id = :employeeId")
    List<Category> findCategoriesByEmployeeId(@Param("employeeId") UUID employeeId);


}
