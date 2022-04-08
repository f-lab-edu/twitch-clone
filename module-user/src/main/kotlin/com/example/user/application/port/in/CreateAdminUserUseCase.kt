package com.example.user.application.port.`in`

import com.example.user.domain.model.AdminUser
import com.example.user.domain.model.NormalUser

interface CreateAdminUserUseCase {
    fun createAdminUser(user: NormalUser, nickname: String): AdminUser
}

