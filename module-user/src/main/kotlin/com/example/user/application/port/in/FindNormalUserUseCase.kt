package com.example.user.application.port.`in`

import com.example.user.application.port.out.SearchUserQuery
import com.example.user.domain.model.NormalUser

interface FindNormalUserUseCase {
    fun findNormalUsers(searchUserQuery: SearchUserQuery): List<NormalUser>
}