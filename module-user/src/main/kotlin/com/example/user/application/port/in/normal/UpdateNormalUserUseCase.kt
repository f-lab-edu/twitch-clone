package com.example.user.application.port.`in`.normal

import com.example.user.application.port.out.NormalUserRepository
import com.example.user.application.service.normal.UpdateNormalUserService
import com.example.user.domain.model.User
import java.util.*

interface UpdateNormalUserUseCase {

    fun updateNormalUser(updateUserCommand: UpdateUserCommand): User

    companion object {
        fun create(normalUserRepository: NormalUserRepository): UpdateNormalUserUseCase = UpdateNormalUserService(normalUserRepository)
    }
}

data class UpdateUserCommand(val id: UUID, val nickname: String?)
