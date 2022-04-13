package com.example.payment.application.port.`in`

import java.util.*

/**
 * 결제
 */
interface PayUseCase {

    /**
     * 추후 결제 번호를 반환 합니다.
     */
    fun pay(payCommand: PayCommand)
}

data class PayCommand(val userId: UUID, val cost: Int)
