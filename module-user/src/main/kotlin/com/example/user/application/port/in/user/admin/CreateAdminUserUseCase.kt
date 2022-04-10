package com.example.user.application.port.`in`.user.admin

import com.example.user.domain.model.AdminUser
import com.example.user.domain.model.NormalUser
import java.util.*

interface CreateAdminUserUseCase {
    fun createAdminUser(createAdminUserCommand: CreateAdminUserCommand): AdminUser
}

data class CreateAdminUserCommand(
    val normalUser: NormalUser,
    val adminUserNickname: String

) {
    val id: UUID
        get() {
            return normalUser.id
        }
}
