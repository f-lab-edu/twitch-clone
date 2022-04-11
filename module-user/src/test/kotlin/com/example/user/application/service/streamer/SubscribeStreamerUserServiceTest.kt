package com.example.user.application.service.streamer

import com.example.payment.application.port.out.PaymentPort
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.util.MockNormalUserRepository
import com.example.user.util.MockPaymentPort
import com.example.user.util.MockStreamerUserRepository
import com.example.user.util.TestUserGenerator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("[스트리머 유저] 구독")
internal class SubscribeStreamerUserServiceTest {

    private lateinit var mockNormalUserRepository: NormalUserRepository
    private lateinit var mockStreamerUserRepository: StreamerUserRepository

    private lateinit var subscribeStreamerUserService: SubscribeStreamerUserService
    private lateinit var mockPaymentPort: PaymentPort

    @BeforeEach
    fun beforeEach() {
        mockNormalUserRepository = MockNormalUserRepository()
        mockStreamerUserRepository = MockStreamerUserRepository()
        mockPaymentPort = MockPaymentPort()
        subscribeStreamerUserService = SubscribeStreamerUserService(mockNormalUserRepository, mockStreamerUserRepository, mockPaymentPort)
    }

    @Test
    @DisplayName("일반 유저가 스트리머 유저를 구독합니다.")
    fun `Normal users subscribe to streamer users`() {
        // given
        val normalUser = TestUserGenerator.normalUser()
        mockNormalUserRepository.save(normalUser)

        val streamUser = TestUserGenerator.streamUser().apply {
            subscriptionCost = 5000
        }
        mockStreamerUserRepository.save(streamUser)

        // when
        subscribeStreamerUserService.subscribe(normalUser.id, streamUser.id)

        // then
        // 현재는 예외가 발생하지 않으면 성공. 결제 기록에 대한 검증을 차후 생성
    }
}
