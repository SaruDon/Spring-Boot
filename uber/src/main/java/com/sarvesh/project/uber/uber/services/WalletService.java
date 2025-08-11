package com.sarvesh.project.uber.uber.services;

import com.sarvesh.project.uber.uber.entities.Ride;
import com.sarvesh.project.uber.uber.entities.User;
import com.sarvesh.project.uber.uber.entities.Wallet;
import com.sarvesh.project.uber.uber.entities.enums.TransactionMethod;

public interface WalletService {
    Wallet createNewWallet(User user);

    Wallet addMoneyToWaller(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    void withDrawAllMyMoneyFromWallet();//wallet to account

    Wallet findWalletById(Long walletId);

    Wallet findWalletByUser(User user);

    Wallet deductMoneyFromWallet(User user, Double amount,String transactionId, Ride ride,TransactionMethod transactionMethod);
}
