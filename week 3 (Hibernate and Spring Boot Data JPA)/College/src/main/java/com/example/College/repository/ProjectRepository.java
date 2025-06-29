package com.example.College.repository;

import com.example.College.dto.ProjectDto;
import com.example.College.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface ProjectRepository extends JpaRepository<Projects,Long> {
}
