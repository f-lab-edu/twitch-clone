package com.example.user.application.port.`in`.user.streamer

import com.example.user.domain.model.StreamerUser

interface RejectStreamerUserUseCase {

    fun rejectStreamerUser(streamerUsers: List<StreamerUser>)

}
