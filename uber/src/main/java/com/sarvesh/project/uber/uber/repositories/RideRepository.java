package com.sarvesh.project.uber.uber.repositories;

import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.Ride;
import com.sarvesh.project.uber.uber.entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride,Long> {
    Page<Ride> findAllByDriver(Driver driver, Pageable pageRequest);
    Page<Ride> findAllByRider(Rider rider, Pageable pageRequest);
}
