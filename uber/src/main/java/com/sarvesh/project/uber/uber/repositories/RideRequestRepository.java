package com.sarvesh.project.uber.uber.repositories;

import com.sarvesh.project.uber.uber.entities.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest,Long> {
}
