package com.example.user.util

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.out.SearchUserQuery
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

    /**
     * email만 검색할 수 있는 임시 메서드 입니다.
     * TODO 동적 쿼리가 지원되게 개선합니다.
     */
    override fun search(searchUserQuery: SearchUserQuery): List<User> {
        return users.values.filter { it.email == searchUserQuery.email }
    }
}
