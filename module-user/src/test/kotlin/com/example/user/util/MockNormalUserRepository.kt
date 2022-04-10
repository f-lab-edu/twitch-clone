package com.example.user.util

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.application.port.out.SearchNormalUserQuery
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

    override fun search(searchNormalUserQuery: SearchNormalUserQuery): List<NormalUser> {
        val searchNormalUsers: List<NormalUser> = emailFilter(searchNormalUserQuery.email).apply {
            nicknameFilter(this, searchNormalUserQuery.nickname)
            statusFilter(this, searchNormalUserQuery.status)
        }
        return searchNormalUsers
    }

    private fun emailFilter(email: String?): List<NormalUser> {
        val values = this.users.values

        return if (email == null) values.toList()
        else values.filter { it.email == email }
    }

    private fun nicknameFilter(searchNormalUsers: List<NormalUser>, nickname: String?): List<NormalUser> {
        return if (nickname == null) searchNormalUsers
        else searchNormalUsers.filter { it.nickname == nickname }
    }

    private fun statusFilter(searchNormalUsers: List<NormalUser>, status: UserStatus?): List<NormalUser> {
        return if (status == null) searchNormalUsers
        else searchNormalUsers.filter { it.status == status }
    }
}
