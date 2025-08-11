package com.sarvesh.project.uber.uber.dto;

import com.sarvesh.project.uber.uber.entities.User;
import com.sarvesh.project.uber.uber.entities.WalletTransaction;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {
    private Long id;

    private UserDto user;

    private Double balance;

    private List<WalletTransactionDto> transactionList;
}
