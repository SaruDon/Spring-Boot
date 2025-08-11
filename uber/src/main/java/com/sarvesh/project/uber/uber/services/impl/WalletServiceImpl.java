package com.sarvesh.project.uber.uber.services.impl;

import com.sarvesh.project.uber.uber.dto.RideDto;
import com.sarvesh.project.uber.uber.dto.WalletDto;
import com.sarvesh.project.uber.uber.dto.WalletTransactionDto;
import com.sarvesh.project.uber.uber.entities.Ride;
import com.sarvesh.project.uber.uber.entities.User;
import com.sarvesh.project.uber.uber.entities.Wallet;
import com.sarvesh.project.uber.uber.entities.WalletTransaction;
import com.sarvesh.project.uber.uber.entities.enums.TransactionMethod;
import com.sarvesh.project.uber.uber.entities.enums.TransactionType;
import com.sarvesh.project.uber.uber.exceptions.ResourceNotFoundException;
import com.sarvesh.project.uber.uber.repositories.WalletRepository;
import com.sarvesh.project.uber.uber.services.WalletService;
import com.sarvesh.project.uber.uber.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    private final WalletTransactionService walletTransactionService;
    private final ModelMapper modelMapper;

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = Wallet
                .builder()
                .balance(0.0)
                .user(user)
                .build();
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet addMoneyToWaller(User user, Double amount,String transactionId, Ride ride,TransactionMethod transactionMethod) {
        Wallet wallet = findWalletByUser(user);
        Double initialBalance = wallet.getBalance();
        wallet.setBalance(initialBalance+amount);

        WalletTransaction walletTransaction =WalletTransaction
                .builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    public void withDrawAllMyMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(()-> new ResourceNotFoundException("Wallet with id"+walletId +"not found"));

    }

    @Override
    public Wallet findWalletByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(()-> new RuntimeException("Wallet with user "+user.getId()+"not found"));
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount,String transactionId, Ride ride,TransactionMethod transactionMethod) {
        Wallet wallet =findWalletByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);

        WalletTransaction walletTransaction =WalletTransaction
                .builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }
}
