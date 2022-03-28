package com.example.user.application.port.out

import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus
import java.util.*

interface StreamerUserRepository {

    fun findAllByStatus(streamerUserStatus: StreamerUserStatus): List<StreamerUser>

    fun save(streamerUser: StreamerUser)

}

