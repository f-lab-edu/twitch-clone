package com.example.user.application.port.`in`.admin

import com.example.user.application.port.out.AdminUserRepository
import com.example.user.application.service.admin.CreateAdminUserService
import com.example.user.domain.model.AdminUser
import com.example.user.domain.model.NormalUser
import java.util.*

interface CreateAdminUserUseCase {
    fun createAdminUser(createAdminUserCommand: CreateAdminUserCommand): AdminUser

    companion object {
        fun create(adminUserRepository: AdminUserRepository): CreateAdminUserUseCase = CreateAdminUserService(adminUserRepository)
    }
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
