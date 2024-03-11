package com.elyadata.sm.repository;

import com.elyadata.sm.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IProjectRepo extends JpaRepository<Project, UUID> {
}
