package com.sbi.ems.repository;

import com.sbi.ems.model.Project;
import com.sbi.ems.model.Project.ProjectStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByStatus(ProjectStatus status);

    Optional<Project> findByName(String name);

    boolean existsByName(String name);
}