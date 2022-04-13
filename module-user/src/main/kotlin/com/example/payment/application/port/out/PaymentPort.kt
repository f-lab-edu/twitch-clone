package com.example.payment.application.port.out

import java.util.*

/**
 * 결제 방법 - 차후 외부 연동하며 블랙박스의 성격으로 운영함
 */
interface PaymentPort {

    /**
     * 결제
     */
    fun pay(userId: UUID, cost: Int): Boolean
}
