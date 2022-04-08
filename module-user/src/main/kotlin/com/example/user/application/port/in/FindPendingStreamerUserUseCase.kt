package com.example.user.application.port.`in`

import com.example.user.domain.model.StreamerUser

interface FindPendingStreamerUserUseCase {

    fun findPendingStreamers() : List<StreamerUser>
}
