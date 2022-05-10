package com.example.user.application.service.admin

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.`in`.admin.CreateAdminUserCommand
import com.example.user.application.port.`in`.admin.CreateAdminUserUseCase
import com.example.user.application.port.out.AdminUserRepository
import com.example.user.domain.model.AdminUser

internal class CreateAdminUserService(private val adminUserRepository: AdminUserRepository) : CreateAdminUserUseCase {

    override fun createAdminUser(createAdminUserCommand: CreateAdminUserCommand): AdminUser {
        val normalUser = createAdminUserCommand.normalUser
        if (adminUserRepository.existsById(normalUser.id)) throw CustomException(ErrorCode.EXISTS_ENTITY)

        return adminUserRepository.save(
            AdminUser(
                id = normalUser.id,
                email = normalUser.email,
                password = normalUser.password,
                nickname = normalUser.nickname,
                createAdminUserCommand.adminUserNickname
            )
        )
    }
}
