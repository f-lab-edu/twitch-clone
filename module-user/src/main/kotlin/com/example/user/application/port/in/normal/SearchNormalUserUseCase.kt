package com.example.user.application.port.`in`.normal

import com.example.user.domain.model.NormalUser
import com.example.user.domain.model.UserStatus

interface SearchNormalUserUseCase {
    fun searchNormalUsers(searchNormalUserQuery: SearchNormalUserQuery): List<NormalUser>
}

class SearchNormalUserQuery(val email: String? = null, val nickname: String? = null, val status: UserStatus? = null)
