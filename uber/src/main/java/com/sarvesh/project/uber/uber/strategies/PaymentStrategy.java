package com.sarvesh.project.uber.uber.strategies;

import com.sarvesh.project.uber.uber.entities.Payment;

public interface PaymentStrategy {

    static final Double PLATFORM_COMMISSION=0.3;

    void processPayment(Payment payment);


}
