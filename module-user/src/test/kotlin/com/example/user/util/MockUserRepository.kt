package com.example.user.util

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.out.UserRepository
import com.example.user.domain.model.User
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
}
