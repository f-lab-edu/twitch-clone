package com.example.user.application.port.`in`

import com.example.user.domain.model.StreamerUser

interface UpdateStreamerUserUseCase {

    fun approveStreamerUser(streamerUsers: List<StreamerUser>)

    fun rejectStreamerUser(streamerUsers: List<StreamerUser>)

}
