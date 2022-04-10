package com.example.user.application.port.`in`.user.streamer

import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus

interface SearchStreamerUserUseCase {
    fun searchStreamers(searchStreamerUserQuery: SearchStreamerUserQuery): List<StreamerUser>
}

class SearchStreamerUserQuery(
    val streamerNickname: String? = null,
    val streamerUserStatus: StreamerUserStatus? = StreamerUserStatus.REGISTERED
)
