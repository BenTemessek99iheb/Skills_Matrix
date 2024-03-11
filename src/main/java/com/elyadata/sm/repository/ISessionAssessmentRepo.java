package com.elyadata.sm.repository;

import com.elyadata.sm.dto.kpis.AverageSkillLevelPerCategory;
import com.elyadata.sm.dto.kpis.EmployeeSkillsByCategory;
import com.elyadata.sm.dto.kpis.SkillPreferenceDTO;
import com.elyadata.sm.dto.kpis.TopNMostRatedSkills;
import com.elyadata.sm.model.Assessment;
import com.elyadata.sm.model.SessionAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ISessionAssessmentRepo extends JpaRepository<SessionAssessment, UUID> {
    @Query("SELECT sa FROM SessionAssessment sa WHERE sa.assessedEmployee.id = :employeeId")
    Optional<SessionAssessment> findByAssessedEmployee(@Param("employeeId") UUID employeeId);

}