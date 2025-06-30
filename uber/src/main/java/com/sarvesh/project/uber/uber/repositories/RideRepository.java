package com.sarvesh.project.uber.uber.repositories;

import com.sarvesh.project.uber.uber.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride,Long> {
}
