package com.sarvesh.project.uber.uber.services;

import com.sarvesh.project.uber.uber.entities.User;
import com.sarvesh.project.uber.uber.entities.Wallet;

public interface WalletService {
    Wallet createNewWallet(User user);
}
