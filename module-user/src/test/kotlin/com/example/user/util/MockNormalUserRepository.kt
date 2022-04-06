package com.example.user.util

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.out.SearchUserQuery
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.domain.model.NormalUser
import com.example.user.domain.model.UserStatus
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class MockNormalUserRepository : NormalUserRepository {

    private val users = ConcurrentHashMap<UUID, NormalUser>()

    override fun save(normalUser: NormalUser): NormalUser {
        users[normalUser.id] = normalUser
        return normalUser
    }

    override fun findById(id: UUID): NormalUser {
        return users[id] ?: throw CustomException(ErrorCode.ENTITY_NOT_FOUND)
    }

    override fun search(searchUserQuery: SearchUserQuery): List<NormalUser> {
        val searchUsers: List<NormalUser> = emailFilter(searchUserQuery.email).apply {
            nicknameFilter(this, searchUserQuery.nickname)
            statusFilter(this, searchUserQuery.status)
        }
        return searchUsers
    }

    private fun emailFilter(email: String?): List<NormalUser> {
        val values = this.users.values

        return if (email == null) values.toList()
        else values.filter { it.email == email }
    }

    private fun nicknameFilter(searchUsers: List<NormalUser>, nickname: String?): List<NormalUser> {
        return if (nickname == null) searchUsers
        else searchUsers.filter { it.nickname == nickname }
    }

    private fun statusFilter(searchUsers: List<NormalUser>, status: UserStatus?): List<NormalUser> {
        return if (status == null) searchUsers
        else searchUsers.filter { it.status == status }
    }
}
