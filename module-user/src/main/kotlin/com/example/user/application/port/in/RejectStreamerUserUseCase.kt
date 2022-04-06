package com.example.user.application.port.`in`

import com.example.user.domain.model.StreamerUser

interface RejectStreamerUserUseCase {

    fun rejectStreamerUser(streamerUsers: List<StreamerUser>)

}
