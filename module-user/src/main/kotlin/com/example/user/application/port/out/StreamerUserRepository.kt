package com.example.user.application.port.out

import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus
import java.util.*

interface StreamerUserRepository {

    fun findById(id: UUID): StreamerUser

    fun findAllByStatus(streamerUserStatus: StreamerUserStatus): List<StreamerUser>

    fun save(streamerUser: StreamerUser)

    fun saveAll(streamerUsers: List<StreamerUser>)

}

