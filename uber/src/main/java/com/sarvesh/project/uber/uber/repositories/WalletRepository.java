package com.sarvesh.project.uber.uber.repositories;

import com.sarvesh.project.uber.uber.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
}
