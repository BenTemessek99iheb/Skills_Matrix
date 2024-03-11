package com.elyadata.sm.repository;

import com.elyadata.sm.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, UUID> {

    @Override
    Page<Employee> findAll(Pageable pageable);

}
