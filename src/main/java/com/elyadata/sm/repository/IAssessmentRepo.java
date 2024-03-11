package com.elyadata.sm.repository;

import com.elyadata.sm.dto.AssessmentDTO;
import com.elyadata.sm.dto.kpis.*;
import com.elyadata.sm.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAssessmentRepo extends JpaRepository<Assessment, UUID> {

    @Query(value = """
            select a1.* from assessment a1
            inner join employee_category ec on  employee_category_id = ec.id and  ec.employee_id = :employeeId
            inner join (select max(a3.created_at) as created_at ,a3.skill_id  from assessment as a3 group by a3.skill_id) a2
            on a1.skill_id =a2.skill_id and a1.created_at =a2.created_at
            order by a1."level" limit 10
             """, nativeQuery = true)
    List<Assessment> findTop10ByEmployeeId(UUID employeeId);


    @Query(value = """
            SELECT new com.elyadata.sm.dto.kpis.SkillPreferenceDTO(s.name, AVG(CASE WHEN a1.preferred = true THEN 1 ELSE 0 END) * 100)
            FROM Assessment a1
            INNER JOIN Skill s ON a1.skill.id = s.id
            GROUP BY s.id, s.name
            """)
    List<SkillPreferenceDTO> findAverageSkillPreferences();

    @Query(value = """
        SELECT new com.elyadata.sm.dto.kpis.SkillPreferenceDTO(s.name, AVG(CASE WHEN a1.preferred = true THEN 1 ELSE 0 END) * 100)
        FROM Assessment a1
        INNER JOIN Skill s ON a1.skill.id = s.id
        WHERE a1.employeeCategory.employee.id = :employeeId
        GROUP BY s.id, s.name
        order by AVG(CASE WHEN a1.preferred = true THEN 1 ELSE 0 END) desc
        """)
    List<SkillPreferenceDTO> findAverageSkillPreferencesById(UUID employeeId);


    @Query(value = """
            SELECT new com.elyadata.sm.dto.kpis.EmployeeSkillsByCategory (c.name, s.name, a.level)
            FROM Assessment a
            INNER JOIN Skill s ON a.skill.id = s.id  
            INNER JOIN Category c ON s.category.id = c.id
            INNER JOIN EmployeeCategory ec ON a.employeeCategory.id = ec.id
            WHERE ec.employee.id = :employeeId and a.lastAssessment = true
         """)
    List<EmployeeSkillsByCategory> getEmployeeSkillsByCategory(UUID employeeId);

    @Query(value = """
                  SELECT new com.elyadata.sm.dto.kpis.AverageSkillLevelPerCategory (c.name , AVG(a.level) )
                      FROM Assessment a
                      INNER JOIN Skill s ON a.skill.id = s.id
                      INNER JOIN Category c ON s.category.id = c.id
                      INNER JOIN EmployeeCategory ec ON a.employeeCategory.id = ec.id
                      WHERE ec.employee.id = :employeeId and a.lastAssessment = true
                      GROUP BY c.name
            """)
    List<AverageSkillLevelPerCategory> averageSkillLevelPerCategory(UUID employeeId); //(@Param("employeeId") String employeeId) // :employeeId


    @Query(value = """
            SELECT new com.elyadata.sm.dto.kpis.TopNMostRatedSkills(s.id, s.name, AVG(a.level))
            FROM Assessment a
            INNER JOIN Skill s ON a.skill.id = s.id
            INNER JOIN EmployeeCategory ec ON a.employeeCategory.id = ec.id
            WHERE ec.employee.id = :employeeId and a.lastAssessment = true
            GROUP BY s.id, s.name
            ORDER BY AVG(a.level) DESC
            """)
    List<TopNMostRatedSkills> findTopSkillsByEmployeeId(UUID employeeId);
    @Query(value = """
        SELECT new com.elyadata.sm.dto.kpis.MatrixForAll (ec.employee.id, c.name, s.name, a.level,a.preferred)
        FROM Assessment a
        INNER JOIN Skill s ON a.skill.id = s.id  
        INNER JOIN Category c ON s.category.id = c.id
        INNER JOIN EmployeeCategory ec ON a.employeeCategory.id = ec.id
            WHERE a.createdAt = (
                                             SELECT MAX(a2.createdAt)\s
                                             FROM Assessment a2\s
                                             WHERE a2.employeeCategory.id = a.employeeCategory.id
                                         )        order by ec.employee.id
     """)

    List<MatrixForAll> getAllEmployeeSkillsByCategory();

//get Asssed skills by Employee Id

    @Query(value = """
                SELECT new com.elyadata.sm.dto.kpis.AssesmentDetails (s.name, a.level,a.preferred,a.certified)
                FROM Assessment a
                INNER JOIN Skill s ON a.skill.id = s.id  
                INNER JOIN EmployeeCategory ec ON a.employeeCategory.id = ec.id
                WHERE ec.employee.id = :employeeId
                ORDER BY a.createdAt DESC
            """)
    List<AssesmentDetails> findAllAssessmentsByEmployeeIdOrderedByDate(UUID employeeId);

    @Query(value = """
                  SELECT new com.elyadata.sm.dto.kpis.AverageSkillLevelPerCategory (c.name , AVG(a.level) )
                      FROM Assessment a
                      INNER JOIN Skill s ON a.skill.id = s.id
                      INNER JOIN Category c ON s.category.id = c.id
                      INNER JOIN EmployeeCategory ec ON a.employeeCategory.id = ec.id
                   
                      GROUP BY c.name
            """)
    List<AverageSkillLevelPerCategory> averageSkillLevelPerCategoryForAll(); 

}