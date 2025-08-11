package com.sarvesh.project.uber.uber.strategies.impl;

import com.sarvesh.project.uber.uber.entities.Payment;
import com.sarvesh.project.uber.uber.entities.Ride;
import com.sarvesh.project.uber.uber.entities.Rider;
import com.sarvesh.project.uber.uber.entities.enums.PaymentStatus;
import com.sarvesh.project.uber.uber.entities.enums.TransactionMethod;
import com.sarvesh.project.uber.uber.repositories.PaymentRepository;
import com.sarvesh.project.uber.uber.services.PaymentService;
import com.sarvesh.project.uber.uber.services.WalletService;
import com.sarvesh.project.uber.uber.services.WalletTransactionService;
import com.sarvesh.project.uber.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {


    private final WalletService walletService;

    private final PaymentRepository paymentRepository;


    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Ride ride = payment.getRide();
        Rider rider = ride.getRider();
        Double commission = payment.getAmount()*PLATFORM_COMMISSION;

        walletService.addMoneyToWaller(
                ride.getDriver().getUser(),
                payment.getAmount()-commission,
                null,
                ride,
                TransactionMethod.RIDE
        );

        walletService.deductMoneyFromWallet(
                rider.getUser(),
                payment.getAmount(),
                null,
                ride,
                TransactionMethod.RIDE
        );


        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
