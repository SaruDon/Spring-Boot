package com.example.Office.repository;

import com.example.Office.entity.AdmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecord,Long> {
}
