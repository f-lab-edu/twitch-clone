package com.example.user.application.port.`in`.user.streamer

import java.util.*

interface UpdateStreamerUserSubscriptionCostUseCase {

    fun updateSubscriptionCost(id: UUID, updateSubscriptionCost: Int)
}
