package com.example.user.application.port.`in`.streamer

import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.application.service.streamer.UpdateStreamerUserService
import java.util.*

interface SuspendStreamerUserUseCase {

    fun suspendStreamer(id: UUID)

    companion object {
        fun create(streamerUserRepository: StreamerUserRepository): SuspendStreamerUserUseCase = UpdateStreamerUserService(streamerUserRepository)
    }
}
