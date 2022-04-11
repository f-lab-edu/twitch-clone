package com.example.user.application.port.`in`.streamer

import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus

interface FindStreamerUserUseCase {
    fun findStreamers(findStreamerUserQuery: FindStreamerUserQuery): List<StreamerUser>
}

class FindStreamerUserQuery(
    val streamerNickname: String? = null,
    val streamerUserStatus: StreamerUserStatus? = StreamerUserStatus.REGISTERED
)
