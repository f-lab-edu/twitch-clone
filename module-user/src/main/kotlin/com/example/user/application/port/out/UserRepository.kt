package com.example.user.application.port.out

import com.example.user.domain.model.User
import java.util.*

interface UserRepository {

    fun save(user: User): User

    fun findById(id: UUID): User

    fun search(searchUserQuery: SearchUserQuery): List<User>
}

class SearchUserQuery(val email: String? = null, val nickname: String? = null)
