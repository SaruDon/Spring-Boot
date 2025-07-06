package com.sarvesh.project.uber.uber.strategies.impl;

import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.Payment;
import com.sarvesh.project.uber.uber.entities.Ride;
import com.sarvesh.project.uber.uber.entities.Wallet;
import com.sarvesh.project.uber.uber.services.WalletService;
import com.sarvesh.project.uber.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CODPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Ride ride = payment.getRide();

        Double commission = payment.getAmount()*PLATFORM_COMMISSION;
        Wallet driverWallet = walletService.findWalletByUser(driver.getUser());

        walletService.deductMoneyFromWallet(driver.getUser(),commission, , , );

    }
}
