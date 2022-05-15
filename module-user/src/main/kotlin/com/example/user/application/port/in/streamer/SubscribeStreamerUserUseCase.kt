package com.example.user.application.port.`in`.streamer

import com.example.payment.application.port.out.PaymentPort
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.application.service.streamer.SubscribeStreamerUserService
import java.util.*

interface SubscribeStreamerUserUseCase {

    fun subscribe(normalUserId: UUID, streamerUserId: UUID)

    companion object {
        fun create(
            normalUserRepository: NormalUserRepository,
            streamerUserRepository: StreamerUserRepository,
            paymentPort: PaymentPort
        ): SubscribeStreamerUserUseCase = SubscribeStreamerUserService(normalUserRepository, streamerUserRepository, paymentPort)
    }
}
