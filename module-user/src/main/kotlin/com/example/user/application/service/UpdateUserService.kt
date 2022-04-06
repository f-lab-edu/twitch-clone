package com.example.user.application.service

import com.example.user.application.port.`in`.SuspendUserUseCase
import com.example.user.application.port.`in`.UpdateUserCommand
import com.example.user.application.port.`in`.UpdateUserUseCase
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.domain.model.User
import java.util.*

internal class UpdateUserService(private val normalUserRepository: NormalUserRepository) : UpdateUserUseCase,
    SuspendUserUseCase {

    /**
     * - 현재 변경 가능한 필드는 nickname만 입니다.
     * - 항상 새로운 객체를 만듭니다.
     */
    override fun updateUser(updateUserCommand: UpdateUserCommand): User {
        return with(updateUserCommand) {
            val normalUser = normalUserRepository.findById(id)
            normalUser.edit().nickname = updateUserCommand.nickname ?: normalUser.nickname
            normalUserRepository.save(normalUser)
        }
    }

    override fun suspendUser(userId: UUID) {
        val user = normalUserRepository.findById(userId)
        user.edit().suspendedUser()
        normalUserRepository.save(user)
    }
}
