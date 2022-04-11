package com.example.user.application.port.`in`.streamer

import com.example.user.domain.model.StreamerUser

interface FindPendingStreamerUserUseCase {

    fun findPendingStreamers(): List<StreamerUser>
}
