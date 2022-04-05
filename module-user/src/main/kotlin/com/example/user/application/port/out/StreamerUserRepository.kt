package com.example.user.application.port.out

import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus
import java.util.*

interface StreamerUserRepository {

    fun existsById(id: UUID): Boolean

    fun findById(id: UUID): StreamerUser

    fun findAllByStatus(streamerUserStatus: StreamerUserStatus): List<StreamerUser>

    fun save(streamerUser: StreamerUser) : StreamerUser

    fun saveAll(streamerUsers: List<StreamerUser>)

    fun findStreamers(searchStreamerQuery: SearchStreamerQuery): List<StreamerUser>

}

class SearchStreamerQuery(val streamerNickname: String? = null,
                          val streamerUserStatus: StreamerUserStatus? = StreamerUserStatus.REGISTERED)

