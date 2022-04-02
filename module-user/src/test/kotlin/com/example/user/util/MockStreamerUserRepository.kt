package com.example.user.util

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.out.SearchStreamerQuery
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus
import com.example.user.domain.model.User
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class MockStreamerUserRepository : StreamerUserRepository {

    private val streamerUsers = ConcurrentHashMap<UUID, StreamerUser>()

    override fun existsById(id: UUID): Boolean {
        return this.streamerUsers[id] != null
    }

    override fun findById(id: UUID): StreamerUser {
        return this.streamerUsers[id] ?: throw CustomException(ErrorCode.ENTITY_NOT_FOUND)
    }

    override fun findAllByStatus(streamerUserStatus: StreamerUserStatus): List<StreamerUser> {
        val streamerUserMap = this.streamerUsers.filterValues {
            it.status == StreamerUserStatus.PENDING
        }
        return ArrayList(streamerUserMap.values)
    }

    override fun save(streamerUser: StreamerUser) : StreamerUser {
        this.streamerUsers[streamerUser.id] = streamerUser
        return streamerUser
    }

    override fun saveAll(streamerUsers: List<StreamerUser>) {
        for (streamerUser in streamerUsers) {
            this.streamerUsers[streamerUser.id] = streamerUser
        }
    }

    override fun findStreamers(searchStreamerQuery: SearchStreamerQuery): List<StreamerUser> {
        val searchStreamers: List<StreamerUser> = nicknameFilter(searchStreamerQuery.streamerNickname).apply {
            statusFilter(this, searchStreamerQuery.streamerUserStatus)
        }
        return searchStreamers
    }

    private fun nicknameFilter(nickname: String?): List<StreamerUser> {
        val values = this.streamerUsers.values
        return if (nickname == null) values.toList()
        else values.filter { it.streamerNickname == nickname }
    }

    private fun statusFilter(searchStreamerUsers: List<StreamerUser>, status: StreamerUserStatus?): List<StreamerUser> {
        return if (status == null) searchStreamerUsers
        else searchStreamerUsers.filter { it.status == status }
    }
}
