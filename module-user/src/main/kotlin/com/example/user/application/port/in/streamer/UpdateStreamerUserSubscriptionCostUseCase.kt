package com.example.user.application.port.`in`.streamer

import java.util.*

interface UpdateStreamerUserSubscriptionCostUseCase {

    fun updateSubscriptionCost(id: UUID, updateSubscriptionCost: Int)
}
