package com.sarvesh.project.uber.uber.strategies.impl;

import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.Payment;
import com.sarvesh.project.uber.uber.entities.Ride;
import com.sarvesh.project.uber.uber.entities.enums.PaymentStatus;
import com.sarvesh.project.uber.uber.entities.enums.TransactionMethod;
import com.sarvesh.project.uber.uber.repositories.PaymentRepository;
import com.sarvesh.project.uber.uber.services.PaymentService;
import com.sarvesh.project.uber.uber.services.WalletService;
import com.sarvesh.project.uber.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Ride ride = payment.getRide();

        Double commission = payment.getAmount()*PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(),commission,null ,ride, TransactionMethod.RIDE );

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
