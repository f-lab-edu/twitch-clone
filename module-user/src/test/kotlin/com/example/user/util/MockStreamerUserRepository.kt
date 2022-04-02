package com.example.user.util

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class MockStreamerUserRepository : StreamerUserRepository {

    private val streamerUsers = ConcurrentHashMap<UUID, StreamerUser>()

    override fun findAllByStatus(streamerUserStatus: StreamerUserStatus): List<StreamerUser> {
        val streamerUserMap = this.streamerUsers.filterValues {
            it.status == StreamerUserStatus.PENDING
        }
        return ArrayList(streamerUserMap.values)
    }

    override fun saveAll(streamerUsers: List<StreamerUser>) {
        for (streamerUser in streamerUsers) {
            this.streamerUsers[streamerUser.id] = streamerUser
        }
    }

    override fun save(streamerUser: StreamerUser) {
        mockCreateStreamer(streamerUser.id, streamerUser)
    }

    override fun findById(id: UUID): StreamerUser {
        return mockSelectStreamer(id)
    }

    private fun mockCreateStreamer(uuid: UUID, streamerUser: StreamerUser) {
        this.streamerUsers[uuid] = streamerUser
    }

    private fun mockSelectStreamer(uuid: UUID): StreamerUser {
        return this.streamerUsers[uuid] ?: throw CustomException(ErrorCode.ENTITY_NOT_FOUND)
    }
}
