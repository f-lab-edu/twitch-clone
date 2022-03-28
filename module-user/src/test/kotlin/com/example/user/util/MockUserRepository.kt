package com.example.user.util

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.out.SearchUserQuery
import com.example.user.application.port.out.UserRepository
import com.example.user.domain.model.User
import com.example.user.domain.model.UserStatus
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class MockUserRepository : UserRepository {

    private val users = ConcurrentHashMap<UUID, User>()

    override fun save(user: User): User {
        val savedUser = User(user.id, user.email, user.password, user.nickname)
        users[user.id] = savedUser

        return savedUser
    }

    override fun findById(id: UUID): User {
        return users[id] ?: throw CustomException(ErrorCode.ENTITY_NOT_FOUND)
    }

    override fun search(searchUserQuery: SearchUserQuery): List<User> {
        val searchUsers: List<User> = emailFilter(searchUserQuery.email).apply {
            nicknameFilter(this, searchUserQuery.nickname)
            statusFilter(this, searchUserQuery.status)
        }
        return searchUsers
    }

    private fun emailFilter(email: String?): List<User> {
        val values = this.users.values

        return if (email == null) values.toList()
        else values.filter { it.email == email }
    }

    private fun nicknameFilter(searchUsers: List<User>, nickname: String?): List<User> {
        return if (nickname == null) searchUsers
        else searchUsers.filter { it.nickname == nickname }
    }

    private fun statusFilter(searchUsers: List<User>, status: UserStatus?): List<User> {
        return if (status == null) searchUsers
        else searchUsers.filter { it.status == status }
    }
}
