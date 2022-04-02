package com.example.user.application.service

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.`in`.CreateAdminUserUseCase
import com.example.user.application.port.out.AdminUserRepository
import com.example.user.domain.model.AdminUser
import com.example.user.domain.model.User

internal class CreateAdminUserService(private val adminUserRepository: AdminUserRepository) : CreateAdminUserUseCase {

    override fun createAdminUser(user: User, nickname: String): AdminUser {
        if (adminUserRepository.findById(user.id) != null) throw CustomException(ErrorCode.EXISTS_ENTITY)

        return adminUserRepository.save(AdminUser(user, nickname))
    }
}
