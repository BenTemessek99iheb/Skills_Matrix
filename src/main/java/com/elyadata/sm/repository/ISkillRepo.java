package com.elyadata.sm.repository;

import com.elyadata.sm.dto.SkillDTO;
import com.elyadata.sm.dto.kpis.SkillsPerCategory;
import com.elyadata.sm.dto.kpis.TopNMostRatedSkills;
import com.elyadata.sm.model.Category;
import com.elyadata.sm.model.Skill;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ISkillRepo extends JpaRepository<Skill, UUID> {
    @EntityGraph(attributePaths = {"category"})
    @Override
    List<Skill> findAll();

    List<Skill> findByCategory(Category category);


    List<Skill> findByCategoryName(String name);

    
    @Query(value = """
            SELECT new  com.elyadata.sm.dto.kpis.TopNMostRatedSkills(s.id,s.name, AVG(a1.level))
            FROM Assessment a1
            INNER JOIN Skill s ON a1.skill.id = s.id
            where a1.lastAssessment = true 
            group by s.id,s.name
            ORDER BY AVG(a1.level) DESC
            LIMIT ?1
            """)
    List<TopNMostRatedSkills> findTopNSkills(int n);


    @Query(value = """
               SELECT s
               from Skill s
               INNER JOIN EmployeeCategory ec ON s.category.id = :categoryId and  ec.category.id= :categoryId
               WHERE ec.employee.id = :employeeId 
            """)
    List<Skill> findSkillsPerCategoryId(UUID employeeId, UUID categoryId);


    //get skills per employee

    @Query(value = """
       SELECT s
       FROM Skill s
       JOIN Assessment a ON s.id = a.skill.id
       WHERE a.employeeCategory.employee.id = :employeeId
    """)
    List<Skill> findSkillsByEmployeeId(UUID employeeId);
}
