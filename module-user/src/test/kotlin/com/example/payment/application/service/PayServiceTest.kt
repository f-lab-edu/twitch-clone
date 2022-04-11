package com.example.payment.application.service

import com.example.payment.application.port.`in`.PayCommand
import com.example.payment.application.port.out.PaymentPort
import com.example.user.util.MockPaymentPort
import com.example.user.util.TestUserGenerator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("[결제방법] 결제")
internal class PayServiceTest {

    private lateinit var mockPaymentPort: PaymentPort
    private lateinit var payService: PayService

    @BeforeEach
    fun beforeEach() {
        mockPaymentPort = MockPaymentPort()
        payService = PayService(mockPaymentPort)
    }

    @Test
    @DisplayName("입력된 비용을 결제한다.")
    fun `Pay the entered cost`() {
        // given
        val normalUser = TestUserGenerator.normalUser()
        val payCommand = PayCommand(normalUser.id, 5000)

        // when
        payService.pay(payCommand)

        // then
        // 현재는 예외가 발생하지 않으면 성공. 결제 기록에 대한 검증을 차후 생성
    }
}
