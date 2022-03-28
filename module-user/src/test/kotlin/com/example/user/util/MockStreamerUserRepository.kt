package com.example.user.util

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.ArrayList

class MockStreamerUserRepository : StreamerUserRepository {

    private val streamerUsers = ConcurrentHashMap<UUID, StreamerUser>()

    override fun findAllByStatus(streamerUserStatus: StreamerUserStatus): List<StreamerUser> {
        val streamerUserMap = this.streamerUsers.filterValues {
            it.status == StreamerUserStatus.PENDING
        }
        return ArrayList(streamerUserMap.values)
    }

    override fun save(streamerUser: StreamerUser) {
        mockCreateStreamer(streamerUser.id(), streamerUser)
    }

    private fun mockCreateStreamer(uuid: UUID, streamerUser: StreamerUser) {
        this.streamerUsers[uuid] = streamerUser
    }
}
