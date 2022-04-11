package com.example.user.application.service.streamer

import com.example.payment.application.port.out.PaymentPort
import com.example.user.application.port.`in`.streamer.SubscribeStreamerUserUseCase
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.application.port.out.StreamerUserRepository
import java.util.*

class SubscribeStreamerUserService(
    private val normalUserRepository: NormalUserRepository,
    private val streamerUserRepository: StreamerUserRepository,
    private val paymentPort: PaymentPort
) : SubscribeStreamerUserUseCase {

    override fun subscribe(normalUserId: UUID, streamerUserId: UUID) {
        val subscriber = normalUserRepository.findById(normalUserId)
        val streamer = streamerUserRepository.findById(streamerUserId)

        paymentPort.pay(subscriber.id, streamer.subscriptionCost)
    }
}
