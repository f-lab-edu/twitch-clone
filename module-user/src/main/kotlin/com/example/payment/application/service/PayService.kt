package com.example.payment.application.service

import com.example.payment.application.port.`in`.PayCommand
import com.example.payment.application.port.`in`.PayUseCase
import com.example.payment.application.port.out.PaymentPort

internal class PayService(private val paymentPort: PaymentPort) : PayUseCase {

    override fun pay(payCommand: PayCommand) {
        with(payCommand) {
            paymentPort.pay(userId, cost)
        }
    }
}
