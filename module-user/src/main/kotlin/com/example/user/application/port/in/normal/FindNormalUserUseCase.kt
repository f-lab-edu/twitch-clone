package com.example.user.application.port.`in`.normal

import com.example.user.domain.model.NormalUser
import com.example.user.domain.model.UserStatus

interface FindNormalUserUseCase {
    fun findNormalUsers(findNormalUserQuery: FindNormalUserQuery): List<NormalUser>
}

class FindNormalUserQuery(val email: String? = null, val nickname: String? = null, val status: UserStatus? = null)
