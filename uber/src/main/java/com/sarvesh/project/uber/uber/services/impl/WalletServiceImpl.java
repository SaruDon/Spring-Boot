package com.sarvesh.project.uber.uber.services.impl;

import com.sarvesh.project.uber.uber.entities.User;
import com.sarvesh.project.uber.uber.entities.Wallet;
import com.sarvesh.project.uber.uber.repositories.WalletRepository;
import com.sarvesh.project.uber.uber.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = Wallet
                .builder()
                .balance(0.0)
                .user(user)
                .build();
        return walletRepository.save(wallet);
    }
}
