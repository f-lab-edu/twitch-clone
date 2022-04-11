package com.example.user.application.port.`in`.streamer

import java.util.*

interface SubscribeStreamerUserUseCase {

    fun subscribe(normalUserId: UUID, streamerUserId: UUID)
}
