package com.sarvesh.project.uber.uber.dto;

import com.sarvesh.project.uber.uber.entities.Ride;
import com.sarvesh.project.uber.uber.entities.Wallet;
import com.sarvesh.project.uber.uber.entities.enums.TransactionMethod;
import com.sarvesh.project.uber.uber.entities.enums.TransactionType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletTransactionDto {

    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private Ride ride;

    private String transactionId;

    private Wallet wallet;

    private LocalDateTime timeStamp;
}
