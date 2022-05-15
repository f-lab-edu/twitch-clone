package com.example.user.application.port.`in`.streamer

import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.application.service.streamer.UpdateStreamerUserService
import com.example.user.domain.model.StreamerUser

interface RejectStreamerUserUseCase {

    fun rejectStreamerUser(streamerUsers: List<StreamerUser>)

    companion object {
        fun create(streamerUserRepository: StreamerUserRepository): RejectStreamerUserUseCase = UpdateStreamerUserService(streamerUserRepository)
    }
}
