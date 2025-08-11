package com.sarvesh.project.uber.uber.repositories;

import com.sarvesh.project.uber.uber.entities.Payment;
import com.sarvesh.project.uber.uber.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByRide(Ride ride);
}
