package com.example.user.application.port.`in`

import com.example.user.domain.model.StreamerUser

interface SelectStreamerUserUseCase {
    fun selectPendingStreamers() : List<StreamerUser>
}
