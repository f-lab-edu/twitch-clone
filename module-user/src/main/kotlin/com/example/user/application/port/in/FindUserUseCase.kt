package com.example.user.application.port.`in`

import com.example.user.application.port.out.SearchUserQuery
import com.example.user.domain.model.User

interface FindUserUseCase {
    fun findUsers(searchUserQuery: SearchUserQuery): List<User>
}
