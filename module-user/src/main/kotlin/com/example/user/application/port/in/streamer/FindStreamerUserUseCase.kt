package com.example.user.application.port.`in`.streamer

import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.application.service.streamer.FindStreamerUserService
import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus

interface FindStreamerUserUseCase {
    fun findStreamers(findStreamerUserQuery: FindStreamerUserQuery): List<StreamerUser>

    companion object {
        fun create(streamerUserRepository: StreamerUserRepository): FindStreamerUserUseCase = FindStreamerUserService(streamerUserRepository)
    }
}

data class FindStreamerUserQuery(
    val streamerNickname: String? = null,
    val streamerUserStatus: StreamerUserStatus? = StreamerUserStatus.REGISTERED
)
