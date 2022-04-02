package com.example.user.application.port.`in`

import com.example.user.domain.model.AdminUser
import com.example.user.domain.model.User

interface CreateAdminUserUseCase {
    fun createAdminUser(user: User, nickname: String): AdminUser
}

