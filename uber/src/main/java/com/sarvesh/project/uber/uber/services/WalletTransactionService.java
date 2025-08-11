package com.sarvesh.project.uber.uber.services;

import com.sarvesh.project.uber.uber.dto.WalletTransactionDto;
import com.sarvesh.project.uber.uber.entities.Wallet;
import com.sarvesh.project.uber.uber.entities.WalletTransaction;
import org.springframework.stereotype.Service;

@Service
public interface WalletTransactionService {
    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
