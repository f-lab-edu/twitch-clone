package com.example.user.application.port.`in`

import com.example.user.domain.model.StreamerUser

interface FindStreamerUserUseCase {
    fun findPendingStreamers() : List<StreamerUser>
}
