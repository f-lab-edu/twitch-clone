package com.example.user.adpter.out

import com.example.payment.application.port.out.PaymentPort
import java.util.*

class PaymentPortImpl : PaymentPort {

    /**
     * mock에서의 결제는 항상 성공함
     */
    override fun pay(userId: UUID, cost: Int): Boolean {
        return true
    }
}
