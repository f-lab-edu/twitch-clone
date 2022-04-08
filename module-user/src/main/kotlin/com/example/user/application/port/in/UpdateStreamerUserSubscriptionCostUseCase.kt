package com.example.user.application.port.`in`

import java.util.*

interface UpdateStreamerUserSubscriptionCostUseCase {

    fun updateSubscriptionCost(id: UUID, updateSubscriptionCost: Long)
}
