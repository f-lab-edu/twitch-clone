package com.example.user.application.port.`in`.streamer

import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.application.service.streamer.UpdateStreamerUserService
import java.util.*

interface UpdateStreamerUserSubscriptionCostUseCase {

    fun updateSubscriptionCost(id: UUID, updateSubscriptionCost: Int)

    companion object {
        fun create(streamerUserRepository: StreamerUserRepository): UpdateStreamerUserSubscriptionCostUseCase =
            UpdateStreamerUserService(streamerUserRepository)
    }
}
