package com.sarvesh.project.uber.uber.services;

import com.sarvesh.project.uber.uber.entities.Payment;
import com.sarvesh.project.uber.uber.entities.Ride;
import com.sarvesh.project.uber.uber.entities.enums.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
